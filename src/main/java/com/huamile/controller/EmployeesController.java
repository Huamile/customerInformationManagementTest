package com.huamile.controller;

import com.huamile.mapper.*;
import com.huamile.service.PermissionService;
import com.huamile.service.impl.CustomershareServiceImpl;
import com.huamile.service.impl.EmployeesServiceImpl;
import com.huamile.utils.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Afa
 */
@Controller
public class EmployeesController {

    @Autowired
    private EmployeesServiceImpl service;

    @Autowired
    private EmployeesExample example;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private CustomershareServiceImpl customershareService;

    @Autowired
    private CustomershareExample customershareExample;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "employees/login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "employees/register";
    }

    @RequestMapping("/toRight")
    public String toRight(){
        return "employees/right";
    }

    @RequiresPermissions("employees:select")
    @RequestMapping("/empSelect")
    public String empSelect(){
        return "employees/findEmpByCondition";
    }

    @RequiresPermissions("employees:backAll")
    @RequestMapping("/empBackAll")
    public String empBackAll(){
        return "employees/findAllEmp";
    }

    @RequiresPermissions("system:updatePassword")
    @RequestMapping("/sysUpdatePassword")
    public String sysUpdatePassword(){
        return "employees/changePassword";
    }

    @RequestMapping("/toModifyInfo")
    public String toModifyInfo(){
        return "employees/modifyInfo";
    }

    @RequestMapping("/login")
    public String login(Employees employees, HttpSession session,Model model){

        List<Permission> permissions = permissionService.selectPinfoByParentId();
        session.setAttribute("menuList",permissions);

        //使用shiro的方式来验证是否登入成功
        String s;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(employees.getEmploginname(), employees.getEmppassword());
        try {
            if (employees.isRemember()){
                token.setRememberMe(true);
            }
            subject.login(token);
            session.setAttribute("loginName",employees.getEmploginname());
            s = "employees/main";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","账号有误");
            s = "employees/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码有误");
            s = "employees/login";
        }
        return s;
    }

    @RequestMapping("/register")
    public String register(@Valid Employees employees, BindingResult result){
        System.out.println("================================== 注册 ======================================");
        String s = "";
        if (result.hasErrors()){
            List<FieldError> list = result.getFieldErrors();
            for (FieldError fieldError : list) {
                System.err.println("出错的属性："+fieldError.getField()+"     提示信息："+fieldError.getDefaultMessage());
            }
        }else {
            Md5Hash md5Hash = new Md5Hash(employees.getEmppassword(), employees.getEmploginname());
            employees.setEmppassword(md5Hash.toString());
            employees.setRoleid(1);
            int i = service.insert(employees);
            System.out.println("======================================="+i+"===================================");
            if (i > 0){
                s = "employees/login";
            }else {
                s = "employees/register";
            }
        }
        return s;
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "employees/login";
    }

    @RequestMapping("/ensurePassword")
    @ResponseBody
    public Map<String,String> ensurePassword(@RequestParam("loginName") String loginName,
                                             @RequestParam("password") String password){
        Md5Hash md5Hash = new Md5Hash(password, loginName);
        password = md5Hash.toString();
        example.clear();
        example.createCriteria().andEmploginnameEqualTo(loginName).andEmppasswordEqualTo(password);
        List<Employees> employees = service.selectByExample(example);
        HashMap<String, String> map = new HashMap<>();
        if (employees.size() > 0){
            map.put("msg","匹配成功");
        }else {
            map.put("msg","与原密码不匹配");
        }
        return map;
    }

    @RequestMapping("/backAllEmp")
    @ResponseBody
    public ResultMap<List<Employees>> backAllEmp(@RequestParam("page") int page,
                                                 @RequestParam("limit") int limit){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            example.clear();
            example.createCriteria().andEmptypeNotEqualTo("超级管理员");
        }else {
            example.clear();
            example.createCriteria().andEmptypeEqualTo("合作专员");
        }
        List<Employees> employees = service.selectByPage(example,page,limit);
        int count = service.countByExample(example);
        return new ResultMap<>(0,"",count,employees);
    }

    @RequestMapping("/backEmpByCondition")
    @ResponseBody
    public ResultMap<List<Employees>> backEmpByCondition(@RequestParam("page") int page,
                                                         @RequestParam("limit") int limit,
                                                         @RequestParam("empname") String empname){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        example.clear();
        if (loginName.equals("huamile") || loginName.equals("qwer")){
            example.createCriteria().andEmpnameLike(empname);
        }else {
            example.createCriteria().andEmpnameLike(empname).andEmptypeEqualTo("合作专员");
        }
        List<Employees> employees = service.selectByPage(example,page,limit);
        int count = service.countByExample(example);
        return new ResultMap<>(0,"",count,employees);
    }

    @RequiresPermissions("employees:insert")
    @RequestMapping("/empInsert")
    @ResponseBody
    public Map<String,Object> empInsert(@RequestBody @Valid Employees employees, BindingResult result){
        HashMap<String, Object> map = new HashMap<>();

        if (result.hasErrors()){
            List<FieldError> list = result.getFieldErrors();
            for (FieldError fieldError : list) {
                System.out.println("出错的属性："+fieldError.getField()+"     提示信息："+fieldError.getDefaultMessage());
            }
        }else {
            int roleid = employees.getRoleid();
            if (roleid == 1){
                employees.setEmptype("合作主管");
            }else if (roleid == 2){
                employees.setEmptype("合作专员");
            }
            Md5Hash md5Hash = new Md5Hash(employees.getEmppassword(), employees.getEmploginname());
            employees.setEmppassword(md5Hash.toString());
            int i = service.insertSelective(employees);

            map.put("num",i);
            if (1 == i){
                map.put("msg","添加成功");
            }else if (0 == i){
                map.put("msg","添加失败");
            }
        }
        return map;
    }

    @RequiresPermissions("employees:delete")
    @RequestMapping("/empDelete")
    @ResponseBody
    public Map<String,Object> empDelete(Integer id){
        HashMap<String, Object> map = new HashMap<>();

        customershareExample.clear();
        customershareExample.createCriteria().andEmpidEqualTo(id);
        List<Customershare> customershares = customershareService.selectByExample(customershareExample);
        if (customershares.size() > 0){
            map.put("num",-1);
            map.put("msg","请先转移专员下的客户");

        }else {
            int i = service.deleteByPrimaryKey(id);
            map.put("num",i);
            if (1 == i){
                map.put("msg","删除成功");
            }else if (0 == i){
                map.put("msg","删除失败");
            }
        }

        return map;
    }


    @RequiresPermissions("employees:update")
    @RequestMapping("/empUpdate")
    @ResponseBody
    public Map<String,Object> empUpdate(@RequestBody @Valid Employees employees, BindingResult result){
        HashMap<String, Object> map = new HashMap<>();

        if (result.hasErrors()){
            List<FieldError> list = result.getFieldErrors();
            for (FieldError fieldError : list) {
                System.out.println("出错的属性："+fieldError.getField()+"     提示信息："+fieldError.getDefaultMessage());
            }
        }else {
            int roleid = employees.getRoleid();
            if (1 == roleid){
                example.clear();
                example.createCriteria().andEmploginnameEqualTo(employees.getEmploginname());
                List<Employees> emp = service.selectByExample(example);
                Integer empid = emp.get(0).getEmpid();
                customershareExample.clear();
                customershareExample.createCriteria().andEmpidEqualTo(empid);
                List<Customershare> customershares = customershareService.selectByExample(customershareExample);
                if (customershares.size() > 0){
                    map.put("num",-1);
                    map.put("msg","请先转移合作专员下的全部客户");
                    return map;
                }else {
                    employees.setEmptype("合作主管");
                }
            }else if (2 == roleid){
                employees.setEmptype("合作专员");
            }
            if (employees.getEmppassword() != null && !employees.getEmppassword().equals("")){
                Md5Hash md5Hash = new Md5Hash(employees.getEmppassword(), employees.getEmploginname());
                employees.setEmppassword(md5Hash.toString());
            }else {
                employees.setEmppassword(null);
            }
            example.clear();
            example.createCriteria().andEmploginnameEqualTo(employees.getEmploginname());
            int i = service.updateByExampleSelective(employees, example);

            map.put("num",i);
            if (1 == i){
                map.put("msg","修改成功");
            }else if (0 == i){
                map.put("msg","修改失败");
            }
        }
        return map;
    }

    @RequestMapping("/empPasswordUpdate")
    @ResponseBody
    public Map<String,Object> empPasswordUpdate(@RequestBody Employees employees){
        String emppassword = employees.getEmppassword();
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        Md5Hash md5Hash = new Md5Hash(emppassword, loginName);
        emppassword = md5Hash.toString();
        example.clear();
        example.createCriteria().andEmploginnameEqualTo(loginName);
        Employees emp = new Employees();
        emp.setEmppassword(emppassword);
        int i = service.updateByExampleSelective(emp, example);
        HashMap<String, Object> map = new HashMap<>();
        map.put("num",i);
        if (1 == i){
            map.put("msg","修改成功");
        }else if (0 == i){
            map.put("msg","修改失败");
        }
        return map;
    }

    @RequestMapping("/ensureRole")
    @ResponseBody
    public Map<String,Object> ensureRole(){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        example.clear();
        example.createCriteria().andEmploginnameEqualTo(loginName);
        List<Employees> employees = service.selectByExample(example);
        int roleid = employees.get(0).getRoleid();
        HashMap<String, Object> map = new HashMap<>();
        map.put("num",roleid);
        return map;
    }

    @RequestMapping("/backSelf")
    @ResponseBody
    public Employees backSelf(){
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        example.clear();
        example.createCriteria().andEmploginnameEqualTo(loginName);
        List<Employees> employees = service.selectByExample(example);
        Employees emp = employees.get(0);
        return emp;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String,Object> checkName(@RequestParam("loginName") String loginName){
        example.clear();
        example.createCriteria().andEmploginnameEqualTo(loginName);
        List<Employees> employees = service.selectByExample(example);
        HashMap<String, Object> map = new HashMap<>();
        if (employees.size() > 0){
            map.put("msg","重名");
        }else {
            map.put("msg","不重名");
        }
        return map;
    }

    @RequestMapping("/ensureHasCus")
    @ResponseBody
    public Map<String,Object> ensureHasCus(@RequestParam("empid") Integer empid){
        HashMap<String, Object> map = new HashMap<>();
        customershareExample.clear();
        customershareExample.createCriteria().andEmpidEqualTo(empid);
        List<Customershare> customershares = customershareService.selectByExample(customershareExample);
        if (customershares.size() > 0){
            map.put("num",1);
        }else {
            map.put("num",0);
        }
        return map;
    }

}
