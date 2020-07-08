package com.huamile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author Afa
 */
@Controller
public class LanguageController {

    @RequestMapping("/changeLanguage")
    public String changeLanguage(Locale locale, HttpServletRequest request){
        return "redirect:"+request.getContextPath()+"/toRegister";
    }
}
