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
            @RequestParam(name = "password",required = false) String password, @RequestParam(name="redirect", required = false) Boolean redirect,
            @RequestParam(name="resend", required = false) Boolean resend, @RequestParam(name="changeNumber", required = false) Boolean changeNumber,
            @RequestParam(name="failedAttempt", required=false) Boolean failedAttempt
    ){       
        ModelAndView mv = new ModelAndView("account-registration/phoneConfirmation");
        User userSess = (User) request.getSession().getAttribute("user");
        if(userSess!=null){
            if(resend != null){
                if(resend){
                    l.info("Resend Number: "+userSess.getPhoneNumber());
                   tv.startVerification(userSess.getPhoneNumber() , "sms");
                   mv.addObject("newAttempt", true);
                   return mv;
                }
            }
            else if(failedAttempt != null){
                if(failedAttempt){
                    mv.addObject("failedAttempt", true);
                    return mv;
                }
            }
            else if(changeNumber != null){
                if(changeNumber){
                    if(number.contains("-")){number = number.replaceAll("-","");}
                    number = "+"+number;
                    l.info("Change Number: "+number);
                    userSess.setPhoneNumber(number);                    
                    VerificationResult vr = tv.startVerification(number, "sms");
                    request.getSession().setAttribute("user", userSess);
                    return mv;
                }
            }
            else if(redirect != null){
                if(redirect){
                    return mv;
            }
            
        }
        }
        if(number.contains("-")){number.replaceAll("-","");}
        number = "+"+number;
        String emailAndPhoneConfirm = userService.checkUserEmailAndPhone(email, number);
        if(emailAndPhoneConfirm.equals("none")){
            HttpSession session = request.getSession();
            tv.startVerification(number, "sms");
            l.info("Initial Registration Number: "+number);
            User user = new User();
            user.setFirstName(firstName);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(LastName);
            user.setUserPassword(password);
            user.setPhoneNumber(number);
            session.setAttribute("user", user);
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
                response.sendRedirect(request.getContextPath()+"/account-created");
            }
            else{
                response.sendRedirect(request.getContextPath()+"/user-signup?failedAttempt=true");
            }
        }
        else{
            response.sendRedirect(request.getContextPath()+"/signup");
        }
    }
    
    @RequestMapping(value="/change-number")
    public ModelAndView changeNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(request.getSession().getAttribute("user")!=null){
            ModelAndView mv = new ModelAndView("account-registration/changeNumber");
            return mv;
        }
        else{
            response.sendRedirect(request.getContextPath()+"/signup");
        }
        return null;
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
            response.sendRedirect(request.getContextPath()+"/signup");
        }
        return null;
    }
}
