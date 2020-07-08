package com.huamile.controller;

import com.huamile.mapper.*;
import com.huamile.service.impl.CustomerServiceImpl;
import com.huamile.service.impl.CustomershareServiceImpl;
import com.huamile.service.impl.EmployeesServiceImpl;
import com.huamile.utils.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Afa
 */
@Controller
public class CustomerShareController {

    @Autowired
    private CustomershareServiceImpl service;

    @Autowired
    private CustomershareExample example;

    @Autowired
    private EmployeesServiceImpl employeesService;

    @Autowired
    private EmployeesExample employeesExample;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CustomerExample customerExample;


    @RequiresPermissions("cs:share")
    @RequestMapping("/csS")
    public String csS(){
        return "customerShare/customerShare";
    }

    @RequiresPermissions("cs:transfer")
    @RequestMapping("/csT")
    public String csT(){
        return "customerShare/customerTransfer";
    }

    @RequiresPermissions("customershare:backAll")
    @RequestMapping("/csBackAll")
    public String csBackAll(){
        return "customerShare/findAllCustomerShare";
    }


    @RequestMapping("/csSelect")
    @ResponseBody
    public Map<String,Object> csSelect(){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        employeesExample.clear();
        employeesExample.createCriteria().andEmptypeEqualTo("合作专员").andEmploginnameNotEqualTo(loginName);
        List<Employees> employees = employeesService.selectByExample(employeesExample);

        employeesExample.clear();
        employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
        List<Employees> employees1 = employeesService.selectByExample(employeesExample);
        Integer empid = employees1.get(0).getEmpid();
        example.clear();
        example.createCriteria().andEmpidEqualTo(empid);
        List<Customershare> customershares = service.selectByExample(example);

        HashMap<String, Object> map = new HashMap<>();
        map.put("cus",customershares);
        map.put("emp",employees);
        return map;
    }

    @RequiresPermissions("customershare:insert")
    @RequestMapping("/csInsert")
    @ResponseBody
    public Map<String,Object> csInsert(@RequestBody() Customershare customershare){
        HashMap<String, Object> map = new HashMap<>();

        example.clear();
        example.createCriteria().andEmpidEqualTo(customershare.getEmpid()).andCusidEqualTo(customershare.getCusid());
        List<Customershare> customershares = service.selectByExample(example);

        if (customershares.size() > 0){
            map.put("num",-1);
            map.put("msg","已存在对应关系，分享失败");
        }else {
            int i = service.insertSelective(customershare);
            map.put("num",i);
            if (1 == i){
                map.put("msg","分享成功");
            }else if (0 == i){
                map.put("msg","分享失败");
            }
        }
        return map;
    }

    @RequiresPermissions("customershare:update")
    @RequestMapping("/csUpdate")
    @ResponseBody
    public Map<String,Object> csUpdate(@RequestBody Customershare customershare){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        List<Customershare> customershares = null;
        HashMap<String, Object> map = new HashMap<>();
        if (!loginName.equals("huamile") && !loginName.equals("qwer")){
            employeesExample.clear();
            employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
            List<Employees> employees = employeesService.selectByExample(employeesExample);
            Integer empid = employees.get(0).getEmpid();
            example.clear();
            example.createCriteria().andEmpidEqualTo(empid).andCusidEqualTo(customershare.getCusid());
            List<Customershare> cs = service.selectByExample(example);
            Integer sid = cs.get(0).getSid();
            customershare.setSid(sid);
            example.clear();
            example.createCriteria().andEmpidEqualTo(customershare.getEmpid()).andCusidEqualTo(customershare.getCusid());
            customershares = service.selectByExample(example);
            if (customershares.size() > 0){
                int i = service.deleteByPrimaryKey(sid);

                map.put("num",i);
                if (i == 1){
                    map.put("msg","转移成功");
                }else if (i == 0){
                    map.put("msg","转移失败");
                }
            }else {
                int j = service.updateByPrimaryKey(customershare);
                map.put("num",j);
                if (j == 1){
                    map.put("msg","转移成功");
                }else if (j == 0){
                    map.put("msg","转移失败");
                }
            }
        }else {
            //待定
        }

        /*int i = 0;
        if (customershares.size() > 0){
            i = service.deleteByPrimaryKey();
            map.put("num",i);
            if (i == 1){
                map.put("msg","转移成功");
            }else if (i == 0){
                map.put("msg","转移失败");
            }
        }else{
            i = service.updateByPrimaryKey(customershare);
            map.put("num",i);
            if (i == 1){
                map.put("msg","转移成功");
            }else if (i == 0){
                map.put("msg","转移失败");
            }
        }*/


        return map;
    }


    @RequestMapping("/backESelect")
    @ResponseBody
    public Map<String,Object> backECSelect(){
        employeesExample.clear();
        employeesExample.createCriteria().andEmptypeEqualTo("合作专员");
        List<Employees> employees = employeesService.selectByExample(employeesExample);

        List<Customer> customers = customerService.selectByExample(null);

        HashMap<String, Object> map = new HashMap<>();
        map.put("emp",employees);
        map.put("cus",customers);
        return map;
    }

    @RequestMapping("/backE2Select")
    @ResponseBody
    public Map<String,Object> backE2CSelect(@RequestParam("cusid") Integer cusid){
        System.err.println(cusid);

        example.clear();
        example.createCriteria().andCusidEqualTo(cusid);
        List<Customershare> customershares = service.selectByExample(example);
        ArrayList<Integer> empids = new ArrayList<>();
        for (Customershare customershare : customershares) {
            empids.add(customershare.getEmpid());
        }

        employeesExample.clear();
        employeesExample.createCriteria().andEmpidIn(empids);
        List<Employees> employees = employeesService.selectByExample(employeesExample);

        HashMap<String, Object> map = new HashMap<>();
        map.put("emp",employees);
        return map;
    }

    @RequestMapping("/backCSelect")
    @ResponseBody
    public Map<String,Object> backCSelect(@RequestParam("empid") Integer empid){
        example.clear();
        example.createCriteria().andEmpidEqualTo(empid);
        List<Customershare> customershares = service.selectByExample(example);

        List<Customer> customers = null;

        if (customershares.size() == 0){
            customers = customerService.selectByExample(null);
        }else {
            ArrayList<Integer> cusids = new ArrayList<>();
            for (Customershare customershare : customershares) {
                cusids.add(customershare.getCusid());
            }
            customerExample.clear();
            customerExample.createCriteria().andCusidNotIn(cusids);
            customers = customerService.selectByExample(customerExample);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("cus",customers);
        return map;
    }

    @RequestMapping("/backAllCs")
    @ResponseBody
    public ResultMap<List<Customershare>> backAllCs(@RequestParam("page") int page,
                                                    @RequestParam("limit") int limit){
        List<Customershare> customershares = service.selectByPage(null, page, limit);
        int count = service.countByExample(null);
        return new ResultMap<>(0,"",count,customershares);
    }

    @RequestMapping("/hasOtherShare")
    @ResponseBody
    public Map<String,Object> hasOtherShare(@RequestParam("id") Integer id){
        example.clear();
        example.createCriteria().andCusidEqualTo(id);
        List<Customershare> customershares = service.selectByExample(example);
        HashMap<String, Object> map = new HashMap<>();
        if (customershares.size() < 1){
            map.put("has",1);
        }else {
            map.put("has",0);
        }
        return map;
    }

    @RequiresPermissions("customershare:delete")
    @RequestMapping("/csDelete")
    @ResponseBody
    public Map<String,Object> csDelete(@RequestParam("cusid") Integer cusid,
                                       @RequestParam("sid") Integer sid){
        example.clear();
        example.createCriteria().andCusidEqualTo(cusid);
        List<Customershare> customershares = service.selectByExample(example);
        HashMap<String, Object> map = new HashMap<>();
        if (customershares.size() > 1){
            int i = service.deleteByPrimaryKey(sid);
            if (1 == i){
                map.put("num",1);
                map.put("msg","删除成功");
            }else if (0 == i){
                map.put("num",0);
                map.put("msg","删除失败");
            }
        }else {
            int i = service.deleteByPrimaryKey(sid);
            int i1 = customerService.deleteByPrimaryKey(cusid);
            if (1 == i && i1 == 1){
                map.put("num",1);
                map.put("msg","删除成功");
            }else {
                map.put("num",0);
                map.put("msg","删除失败");
            }
        }
        return map;
    }

    @RequiresPermissions("customershare:add")
    @RequestMapping("/csAdd")
    @ResponseBody
    public Map<String,Object> csAdd(@RequestBody Customershare customershare){
        example.clear();
        example.createCriteria().andCusidEqualTo(customershare.getCusid()).andEmpidEqualTo(customershare.getEmpid());
        List<Customershare> customershares = service.selectByExample(example);
        HashMap<String, Object> map = new HashMap<>();
        if (customershares.size() > 0){
            map.put("num",-1);
            map.put("msg","已存在对应关系，分享失败");
        }else {
            int i = service.insertSelective(customershare);

            map.put("num",i);
            if (i == 1){
                map.put("msg","添加成功");
            }else if (i == 0){
                map.put("msg","添加失败");
            }
        }
        return map;
    }

    @RequiresPermissions("customershare:modify")
    @RequestMapping("/csModify")
    @ResponseBody
    public Map<String,Object> csModify(@RequestBody Customershare customershare){
        example.clear();
        example.createCriteria().andEmpidEqualTo(customershare.getEmpid()).andCusidEqualTo(customershare.getCusid());
        List<Customershare> customershares = service.selectByExample(example);
        HashMap<String, Object> map = new HashMap<>();
        int i = 0;
        if (customershares.size() > 0){
            i = service.deleteByPrimaryKey(customershare.getSid());
            map.put("num",i);
            if (i == 1){
                map.put("msg","修改成功");
            }else if (i == 0){
                map.put("msg","修改失败");
            }
        }else{
            i = service.updateByPrimaryKey(customershare);
            map.put("num",i);
            if (i == 1){
                map.put("msg","修改成功");
            }else if (i == 0){
                map.put("msg","修改失败");
            }
        }
        return map;
    }




}
