package com.huamile.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Afa
 */
public class MyException implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav = new ModelAndView();

        /*if(ex instanceof MySQLIntegrityConstraintViolationException || ex instanceof SQLException){
            mav.addObject("errorMessage", handler.toString());
            mav.addObject("errorDetail", "数据库异常");
        }else if(ex instanceof IllegalStateException){
            mav.addObject("errorMessage", handler.toString());
            mav.addObject("errorDetail", "非法数字");
        }else{
            mav.addObject("errorMessage", handler.toString());
            mav.addObject("errorDetail", ex.toString());
        }*/

        mav.setViewName("errorPage");

        return mav;

    }
}
