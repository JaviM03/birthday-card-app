/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.domain.User;
import com.christmascards.service.UserService;
import com.christmascards.util.TwilioVerification;
import com.christmascards.util.VerificationResult;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class AccountRegistrationController {
    
    Logger l = Logger.getLogger("logger");
    
    @Autowired
    UserService userService;
    
    TwilioVerification tv = new TwilioVerification();
    
    @RequestMapping(value="/signup")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("account-registration/register");
        return mv;
    }
    
    @RequestMapping(value="/user-signup")
    public ModelAndView signUpUser(HttpServletRequest request, @RequestParam(name = "firstName",required = false) String firstName, @RequestParam(name = "lastName",required = false) String LastName,
            @RequestParam(name = "email",required = false) String email, @RequestParam(name = "phoneNumber",required = false) String number,
            @RequestParam(name = "password",required = false) String password, @RequestParam(name="redirect", required = false) String redirect
    ){
        number = "+"+number;
        String emailAndPhoneConfirm = userService.checkUserEmailAndPhone(email, number);
        if(redirect != null){
            if(redirect.equals("true")){
                ModelAndView mv = new ModelAndView("account-registration/phoneConfirmation");
                mv.addObject("failedAttempt", true);
                return mv;
            }
        }
        if(emailAndPhoneConfirm.equals("none")){
            HttpSession session = request.getSession();
            tv.startVerification(number.replaceAll("-", ""), "sms");
            User user = new User();
            user.setFirstName(firstName);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(LastName);
            user.setUserPassword(password);
            session.setAttribute("user", user);
            ModelAndView mv = new ModelAndView("account-registration/phoneConfirmation");
            return mv;
        }
        else {
            return new ModelAndView("account-registration/register");
        }
    }
    
    @RequestMapping(value="/phone-confirmation", method = RequestMethod.POST)
    public void phoneConfirmation(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            VerificationResult vr =  tv.checkVerification(user.getPhoneNumber(), code);
            if(vr.isValid()){
                try{
                    userService.registerUser(user);
                }
                catch(Exception e){
                    
                }
                response.sendRedirect("/");
            }
            else{
                response.sendRedirect("/user-signup?redirect=true");
            }
        }
        else{
            response.sendRedirect("/signup");
        }
    }
    
    @RequestMapping(value="/account-created")
    public ModelAndView accountCreated(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            ModelAndView mv = new ModelAndView("account-registration/successfulAccCreation");
            return mv;
        }
        else {
            response.sendRedirect("/signup");
        }
        return null;
    }
}
