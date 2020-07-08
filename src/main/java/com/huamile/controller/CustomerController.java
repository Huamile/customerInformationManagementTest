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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Afa
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerServiceImpl service;

    @Autowired
    private CustomerExample example;

    @Autowired
    private EmployeesServiceImpl employeesService;

    @Autowired
    private EmployeesExample employeesExample;

    @Autowired
    private CustomervisitServiceImpl customervisitService;

    @Autowired
    private CustomervisitExample customervisitExample;

    @Autowired
    private CustomershareServiceImpl customershareService;

    @Autowired
    private CustomershareExample customershareExample;

    @RequiresPermissions("customer:backAll")
    @RequestMapping("/cusBackAll")
    public String cusBackAll(){
        return "customer/lookAllCustomer";
    }

    @RequiresPermissions("customer:select")
    @RequestMapping("/cusSelect")
    public String cusSelect(){
        return "customer/findOwnCusByCondition";
    }

    @RequiresPermissions("customer:backOwn")
    @RequestMapping("/cusBackOwn")
    public String cusBackOwn(HttpSession session){
        employeesExample.clear();
        employeesExample.createCriteria().andEmploginnameEqualTo((String) session.getAttribute("loginName"));
        List<Employees> employees = employeesService.selectByExample(employeesExample);
        String empname = employees.get(0).getEmpname();
        session.setAttribute("empname",empname);
        return "customer/findOwnCus";
    }

    @RequestMapping("/backAllCus")
    @ResponseBody
    public ResultMap<List<Customer>> backAllCus(@RequestParam("page") int page,
                                                @RequestParam("limit") int limit){
        List<Customer> customers = service.selectByPage(null, page, limit);
        int count = service.countByExample(null);
        return new ResultMap<>(0,"",count,customers);
    }

    @RequestMapping("/backOwnCus")
    @ResponseBody
    public ResultMap<List<Customer>> backOwnCus(@RequestParam("page") int page,
                                                @RequestParam("limit") int limit){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        List<Customer> customers = null;
        int count = 0;
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            customers = service.selectByPage(null,page,limit);
            count = service.countByExample(null);
        }else {
            /*通过登录名在employees表找到empid*/
            employeesExample.clear();
            employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
            List<Employees> employees = employeesService.selectByExample(employeesExample);
            Integer empid = employees.get(0).getEmpid();
            /*通过empid在分享表找到有联系的cusid,并存进cusids中*/
            customershareExample.clear();
            customershareExample.createCriteria().andEmpidEqualTo(empid);
            List<Customershare> customershares = customershareService.selectByExample(customershareExample);
            ArrayList<Integer> cusids = new ArrayList<>();
            for (Customershare customershare : customershares) {
                cusids.add(customershare.getCusid());
            }
            /*通过cusids中的cusid找到customer*/
            example.clear();
            example.createCriteria().andCusidIn(cusids);
            customers = service.selectByPage(example, page, limit);
            count = service.countByExample(example);
        }
        return new ResultMap<>(0,"",count,customers);
    }


    @RequiresPermissions("customer:delete")
    @RequestMapping("/cusDelete")
    @ResponseBody
    public Map<String,Object> cusDelete(@RequestParam("id") Integer id){
        int j = 0;
        int k = 0;
        HashMap<String, Object> map = new HashMap<>();
        customervisitExample.clear();
        customervisitExample.createCriteria().andCusidEqualTo(id);
        List<Customervisit> customervisits = customervisitService.selectByExample(customervisitExample);
        if (customervisits.size() > 0){
            j = customervisitService.deleteBycusid(id);
            k = customershareService.deleteBycusId(id);
            int i = service.deleteByPrimaryKey(id);
            if (1 == i && j >= 1 && k == 1){
                map.put("num",1);
                map.put("msg","删除成功");
            }else {
                map.put("num",0);
                map.put("msg","删除失败");
            }
        }else {
            k = customershareService.deleteBycusId(id);
            int i = service.deleteByPrimaryKey(id);
            if (1 == i && k == 1){
                map.put("num",1);
                map.put("msg","删除成功");
            }else {
                map.put("num",0);
                map.put("msg","删除失败");
            }
        }
        return map;
    }

    @RequiresPermissions("customer:insert")
    @RequestMapping("/cusInsert")
    @ResponseBody
    public Map<String,Object> empInsert(@RequestBody @Valid Customer customer, BindingResult result){
        HashMap<String, Object> map = new HashMap<>();
        if (result.hasErrors()){
            List<FieldError> list = result.getFieldErrors();
            for (FieldError fieldError : list) {
                System.out.println("出错的属性："+fieldError.getField()+"     提示信息："+fieldError.getDefaultMessage());
            }
        }else {
            Integer empid = Integer.valueOf(customer.getContact());
            Employees emp = employeesService.selectByPrimaryKey(empid);
            String empname = emp.getEmpname();
            customer.setContact(empname);
            int i = service.insertSelective(customer);
            Integer cusid = customer.getCusid();
            Customershare customershare = new Customershare();
            customershare.setEmpid(empid);
            customershare.setCusid(cusid);
            int j = customershareService.insertSelective(customershare);
            if (1 == i && j == 1){
                map.put("num",1);
                map.put("msg","添加成功");
            }else {
                map.put("num",0);
                map.put("msg","添加失败");
            }
        }
        return map;
    }

    @RequiresPermissions("customer:update")
    @RequestMapping("/cusUpdate")
    @ResponseBody
    public Map<String,Object> cusUpdate(@RequestBody @Valid Customer customer, BindingResult result){
        HashMap<String, Object> map = new HashMap<>();
        if (result.hasErrors()){
            List<FieldError> list = result.getFieldErrors();
            for (FieldError fieldError : list) {
                System.out.println("出错的属性："+fieldError.getField()+"     提示信息："+fieldError.getDefaultMessage());
            }
        }else {
            Integer empid = Integer.valueOf(customer.getContact());
            Employees emp = employeesService.selectByPrimaryKey(empid);
            String empname = emp.getEmpname();
            customer.setContact(empname);
            int i = service.updateByPrimaryKeySelective(customer);
            map.put("num",i);
            if (1 == i){
                map.put("msg","修改成功");
            }else if (0 == i){
                map.put("msg","修改失败");
            }
        }
        return map;
    }

    @RequestMapping("/backCusByCondition")
    @ResponseBody
    public ResultMap<List<Customer>> backCusByCondition(@RequestParam("page") int page,
                                                        @RequestParam("limit") int limit,
                                                        @RequestParam("cusname") String cusname,
                                                        @RequestParam("contact") Integer contact){
        example.clear();
        if (contact == null){
            example.createCriteria().andCusnameLike(cusname);
        }else {
            Employees employees = employeesService.selectByPrimaryKey(contact);
            String empname = employees.getEmpname();
            example.createCriteria().andCusnameLike(cusname).andContactEqualTo(empname);
        }
        List<Customer> customers = service.selectByPage(example, page, limit);
        int count = service.countByExample(example);
        return new ResultMap<>(0,"",count,customers);
    }

    @RequestMapping("/backOwnCusByCondition")
    @ResponseBody
    public ResultMap<List<Customer>> backOwnCusByCondition(@RequestParam("page") int page,
                                                           @RequestParam("limit") int limit,
                                                           @RequestParam("cusname") String cusname,
                                                           @RequestParam("contact") Integer empid){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            if (empid != null){
                customershareExample.clear();
                customershareExample.createCriteria().andEmpidEqualTo(empid);
                List<Customershare> customershares = customershareService.selectByExample(customershareExample);
                ArrayList<Integer> cusids = new ArrayList<>();
                for (Customershare customershare : customershares) {
                    cusids.add(customershare.getCusid());
                }
                example.clear();
                example.createCriteria().andCusidIn(cusids).andCusnameLike(cusname);
            }else {
                example.clear();
                example.createCriteria().andCusnameLike(cusname);
            }
        }else {
            employeesExample.clear();
            employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
            List<Employees> employees = employeesService.selectByExample(employeesExample);
            Integer empid1 = employees.get(0).getEmpid();

            customershareExample.clear();
            customershareExample.createCriteria().andEmpidEqualTo(empid1);
            List<Customershare> customershares = customershareService.selectByExample(customershareExample);
            ArrayList<Integer> cusids = new ArrayList<>();
            for (Customershare customershare : customershares) {
                cusids.add(customershare.getCusid());
            }
            example.clear();
            example.createCriteria().andCusidIn(cusids).andCusnameLike(cusname);
        }
        List<Customer> customers = service.selectByPage(example, page, limit);
        int count = service.countByExample(example);
        return new ResultMap<>(0,"",count,customers);
    }


    @RequestMapping("/backSelect")
    @ResponseBody
    public Map<String,Object> backSelect(){
        employeesExample.clear();
        employeesExample.createCriteria().andEmptypeEqualTo("合作专员");
        List<Employees> employees = employeesService.selectByExample(employeesExample);
        HashMap<String, Object> map = new HashMap<>();
        map.put("emp",employees);
        return map;
    }

    @RequestMapping("/backEmp")
    @ResponseBody
    public Employees backEmp(){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        employeesExample.clear();
        employeesExample.createCriteria().andEmploginnameEqualTo(loginName);
        List<Employees> employees = employeesService.selectByExample(employeesExample);
        Employees emp = employees.get(0);
        return emp;
    }

    @RequestMapping("/backContact")
    @ResponseBody
    public Employees backContact(@RequestParam("contact") String contact){
        employeesExample.clear();
        employeesExample.createCriteria().andEmpnameEqualTo(contact);
        List<Employees> employees = employeesService.selectByExample(employeesExample);
        Employees emp = employees.get(0);
        return emp;
    }



}
