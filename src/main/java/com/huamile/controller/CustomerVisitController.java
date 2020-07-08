package com.huamile.controller;

import com.huamile.mapper.*;
import com.huamile.service.impl.CustomerServiceImpl;
import com.huamile.service.impl.CustomershareServiceImpl;
import com.huamile.service.impl.CustomervisitServiceImpl;
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

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Afa
 */
@Controller
public class CustomerVisitController {

    @Autowired
    private CustomervisitServiceImpl service;

    @Autowired
    private CustomervisitExample example;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CustomerExample customerExample;

    @Autowired
    private EmployeesServiceImpl employeesService;

    @Autowired
    private EmployeesExample employeesExample;

    @Autowired
    private CustomershareServiceImpl customershareService;

    @Autowired
    private CustomershareExample customershareExample;

    @RequiresPermissions("customervisit:backAll")
    @RequestMapping("/cvBackAll")
    public String cvBackAll(){
        return "customerVisit/lookAllCustomerVisit";
    }

    @RequiresPermissions("customervisit:select")
    @RequestMapping("/cvSelect")
    public String cvSelect(){
        return "customerVisit/findOwnCvByCondition";
    }

    @RequiresPermissions("customervisit:backOwn")
    @RequestMapping("/cvBackOwn")
    public String cvBackOwn(HttpSession session){
        employeesExample.clear();
        employeesExample.createCriteria().andEmploginnameEqualTo((String) session.getAttribute("loginName"));
        List<Employees> employees = employeesService.selectByExample(employeesExample);
        String empname = employees.get(0).getEmpname();
        session.setAttribute("empname",empname);
        return "customerVisit/findOwnCv";
    }

    @RequestMapping("/backAllCv")
    @ResponseBody
    public ResultMap<List<Customervisit>> backAllCv(@RequestParam("page") int page,
                                                    @RequestParam("limit") int limit){
        List<Customervisit> customervisits = service.selectByPage(null, page, limit);
        int i = service.countByExample(null);
        return new ResultMap<>(0,"",i,customervisits);
    }

    @RequestMapping("/backCusSelect")
    @ResponseBody
    public Map<String,Object> backCusSelect(){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        List<Customer> customers = null;
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            customers = customerService.selectByExample(null);
        }else {
            employeesExample.clear();
            employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
            List<Employees> employees = employeesService.selectByExample(employeesExample);
            String emptype = employees.get(0).getEmptype();
            if (emptype.equals("合作主管")){
                customers = customerService.selectByExample(null);
            }else {
                Integer empid = employees.get(0).getEmpid();

                customershareExample.clear();
                customershareExample.createCriteria().andEmpidEqualTo(empid);
                List<Customershare> customershares = customershareService.selectByExample(customershareExample);
                ArrayList<Integer> cusids = new ArrayList<>();
                for (Customershare customershare : customershares) {
                    cusids.add(customershare.getCusid());
                }

                customerExample.clear();
                customerExample.createCriteria().andCusidIn(cusids);
                customers = customerService.selectByExample(customerExample);
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("cus",customers);
        return map;
    }

    @RequestMapping("/backOwnCv")
    @ResponseBody
    public ResultMap<List<Customervisit>> backOwnCv(@RequestParam("page") int page,
                                                    @RequestParam("limit") int limit){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        List<Customervisit> customervisits = null;
        int count = 0;
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            customervisits = service.selectByPage(null, page, limit);
            count = service.countByExample(null);
        }else {
            employeesExample.clear();
            employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
            List<Employees> employees = employeesService.selectByExample(employeesExample);
            Integer empid = employees.get(0).getEmpid();
            example.clear();
            example.createCriteria().andEmpidEqualTo(empid);
            customervisits = service.selectByPage(example, page, limit);
            count = service.countByExample(example);
        }
        return new ResultMap<>(0,"",count,customervisits);
    }

    @RequiresPermissions("customervisit:delete")
    @RequestMapping("/cvDelete")
    @ResponseBody
    public Map<String,Object> cvDelete(Integer id){
        int i = service.deleteByPrimaryKey(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("num",i);
        if (1 == i){
            map.put("msg","删除成功");
        }else if (0 == i){
            map.put("msg","删除失败");
        }
        return map;
    }

    @RequiresPermissions("customervisit:insert")
    @RequestMapping("/cvInsert")
    @ResponseBody
    public Map<String,Object> cvInsert(@RequestBody Customervisit customervisit){
        int i = service.insertSelective(customervisit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("num",i);
        if (1 == i){
            map.put("msg","添加成功");
        }else if (0 == i){
            map.put("msg","添加失败");
        }
        return map;
    }

    @RequiresPermissions("customervisit:update")
    @RequestMapping("/cvUpdate")
    @ResponseBody
    public Map<String,Object> cusUpdate(@RequestBody Customervisit customervisit){
        int i = service.updateByPrimaryKeySelective(customervisit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("num",i);
        if (1 == i){
            map.put("msg","修改成功");
        }else if (0 == i){
            map.put("msg","修改失败");
        }
        return map;
    }



    @RequestMapping("/backCvByCondition")
    @ResponseBody
    public ResultMap<List<Customervisit>> backCvByCondition(@RequestParam("page") int page,
                                                            @RequestParam("limit") int limit,
                                                            @RequestParam("cusname") Integer cusid,
                                                            @RequestParam("date") String date){
        List<Customervisit> customervisits = null;
        int count = 0;
        example.clear();
        CustomervisitExample.Criteria criteria = example.createCriteria();
        /*date不为空*/
        if (!date.equals("")){
            /*定义时间转化格式*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            /*将时间字符串拆分*/
            String[] split = date.split(" - ");
            /*定义两个空Date容器来装时间*/
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = simpleDateFormat.parse(split[0]);
                endDate = simpleDateFormat.parse(split[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andDateBetween(startDate,endDate);
        }
        /*cusid不为空*/
        if (cusid != null){
            criteria.andCusidEqualTo(cusid);
        }
        if (cusid == null && date.equals("")){
            customervisits = service.selectByPage(null, page, limit);
            count = service.countByExample(null);
        }else {
            customervisits = service.selectByPage(example, page, limit);
            count = service.countByExample(example);
        }
        return new ResultMap<>(0,"",count,customervisits);
    }


    @RequestMapping("/backOwnCvByCondition")
    @ResponseBody
    public ResultMap<List<Customervisit>> backOwnCvByCondition(@RequestParam("page") int page,
                                                               @RequestParam("limit") int limit,
                                                               @RequestParam("cusname") Integer cusid,
                                                               @RequestParam("date") String date){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        List<Customervisit> customervisits = null;
        int count = 0;
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            example.clear();
            CustomervisitExample.Criteria criteria = example.createCriteria();
            /*date不为空*/
            if (!date.equals("")){
                /*定义时间转化格式*/
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                /*将时间字符串拆分*/
                String[] split = date.split(" - ");
                /*定义两个空Date容器来装时间*/
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = simpleDateFormat.parse(split[0]);
                    endDate = simpleDateFormat.parse(split[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                criteria.andDateBetween(startDate,endDate);
            }
            /*cusid不为空*/
            if (cusid != null){
                criteria.andCusidEqualTo(cusid);
            }
            if (cusid == null && date.equals("")){
                customervisits = service.selectByPage(null, page, limit);
                count = service.countByExample(null);
            }else {
                customervisits = service.selectByPage(example, page, limit);
                count = service.countByExample(example);
            }
        }else {
            example.clear();
            CustomervisitExample.Criteria criteria = example.createCriteria();
            employeesExample.clear();
            employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
            List<Employees> employees = employeesService.selectByExample(employeesExample);
            Integer empid = employees.get(0).getEmpid();
            criteria.andEmpidEqualTo(empid);
            /*date不为空*/
            if (!date.equals("")){
                /*定义时间转化格式*/
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                /*将时间字符串拆分*/
                String[] split = date.split(" - ");
                /*定义两个空Date容器来装时间*/
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = simpleDateFormat.parse(split[0]);
                    endDate = simpleDateFormat.parse(split[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                criteria.andDateBetween(startDate,endDate);
            }
            /*cusid不为空*/
            if (cusid != null){
                criteria.andCusidEqualTo(cusid);
            }
            customervisits = service.selectByPage(example, page, limit);
            count = service.countByExample(example);
        }
        return new ResultMap<>(0,"",count,customervisits);
    }

    @RequestMapping("/hasVisit")
    @ResponseBody
    public Map<String,Object> hasVisit(@RequestParam("id") Integer id){
        example.clear();
        example.createCriteria().andCusidEqualTo(id);
        List<Customervisit> customervisits = service.selectByExample(example);
        HashMap<String, Object> map = new HashMap<>();
        if (customervisits.size() > 0){
            map.put("has",1);
        }else {
            map.put("has",0);
        }
        return map;
    }


}
