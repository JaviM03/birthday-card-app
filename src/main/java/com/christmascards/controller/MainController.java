/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.domain.User;
import com.christmascards.service.UserService;
import com.christmascards.util.LoginVerification;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HP PC
 */
@Controller
public class MainController {
    
    @Autowired
    UserService us;        
    
    Logger l = Logger.getLogger("logger");
    
    public static String REFERREDCODE;
    
    @RequestMapping(value="/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }   
    
    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="attempt", required=false) Boolean attempt) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            if(REFERREDCODE==null){
                response.sendRedirect(request.getContextPath()+"/dashboard");
            }
            else{
                response.sendRedirect(request.getContextPath()+"/referral/info");
            }
        }
        ModelAndView mv = new ModelAndView("login");
        if(attempt!=null){if(attempt){mv.addObject("failedLogin", true);}}
        return mv;
    }
    
    @RequestMapping(value="/attempt-login")
    public void attemptLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="email") String email, @RequestParam(name="password") String password) throws IOException{
        User user = us.authenticateUser(email, password);
        if(user!=null){
            request.getSession().setAttribute("loggedUser", user);
            if(REFERREDCODE==null){
                response.sendRedirect(request.getContextPath()+"/dashboard");
            }
            else{
                response.sendRedirect(request.getContextPath()+"/referral/info");
            }
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login?attempt=true");
        }
    }
    
    
    @RequestMapping(value="/forgot-password")
    public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("password-reset");
    }
    
   
    
}
