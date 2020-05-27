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
    public ModelAndView signUpUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "firstName",required = false) String firstName, @RequestParam(name = "lastName",required = false) String LastName,
            @RequestParam(name = "email",required = false) String email, @RequestParam(name = "phoneNumber",required = false) String number,
            @RequestParam(name = "password",required = false) String password, @RequestParam(name="redirect", required = false) Boolean redirect,
            @RequestParam(name="resend", required = false) Boolean resend, @RequestParam(name="changeNumber", required = false) Boolean changeNumber,
            @RequestParam(name="failedAttempt", required=false) Boolean failedAttempt
    ) throws IOException{       
        ModelAndView mv = new ModelAndView("");

        number = number.replace("[^0-9]", "");
        number = "+"+number;
        String emailAndPhoneConfirm = userService.checkUserEmailAndPhone(email, number);
        System.out.println("Email And Phone Confirm: " + emailAndPhoneConfirm);
        if(emailAndPhoneConfirm.equals("none")){
            User user = new User();
            user.setFirstName(firstName);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(LastName);
            user.setUserPassword(password);
            user.setPhoneNumber(number);
            user = userService.registerUser(user);
            request.getSession().setAttribute("loggedUser", user);
            response.sendRedirect(request.getContextPath()+"/user-created"); 
            return null;
        }
        else {
            response.sendRedirect(request.getContextPath()+"/login?emailOrPhoneTaken=true"); 
            return null;
        }
    }
    
    @RequestMapping(value="/user-created")
    public ModelAndView accountCreatedSuccess(HttpServletRequest request, HttpServletResponse response
    ) throws IOException{       
        return new ModelAndView("account-registration/successfulAccCreationV2");
    }
    
    /* Old Registration Form 
    @RequestMapping(value="/user-signup")
    public ModelAndView signUpUser(HttpServletRequest request, @RequestParam(name = "firstName",required = false) String firstName, @RequestParam(name = "lastName",required = false) String LastName,
            @RequestParam(name = "email",required = false) String email, @RequestParam(name = "phoneNumber",required = false) String number,
            @RequestParam(name = "password",required = false) String password, @RequestParam(name="redirect", required = false) Boolean redirect,
            @RequestParam(name="resend", required = false) Boolean resend, @RequestParam(name="changeNumber", required = false) Boolean changeNumber,
            @RequestParam(name="failedAttempt", required=false) Boolean failedAttempt
    ){       
        ModelAndView mv = new ModelAndView("account-registration/phoneConfirmation");
        HttpSession session = request.getSession();
        User userSess = (User) session.getAttribute("user");
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
                    number = number.replace("[^0-9]", "");
                    //number = "+1"+number;
                    l.info("Changed Number: "+number);
                    userSess.setPhoneNumber("+"+number);                    
                    VerificationResult vr = tv.startVerification(number, "sms");
                    session.setAttribute("user", userSess);
                    return mv;
                }
            }
            else if(redirect != null){
                if(redirect){
                    return mv;
            }
            
        }
        }
        number = number.replace("[^0-9]", "");
        number = "+"+number;
        l.info("Initial Registration Number: "+number);
        String emailAndPhoneConfirm = userService.checkUserEmailAndPhone(email, number);
        if(emailAndPhoneConfirm.equals("none")){
            VerificationResult vr = tv.startVerification(number, "sms");
            if(!vr.isValid()){
                System.out.println(vr.getErrors()[0]);
                mv = new ModelAndView("account-registration/register");
                mv.addObject("invalidPhoneNumber", true);
                return mv;
            }
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
            mv = new ModelAndView("account-registration/register");
            mv.addObject("emailAndPhoneAlreadyExist", true);
            return mv;
        }
    }
    
    @RequestMapping(value="/phone-confirmation", method = RequestMethod.POST)
    public void phoneConfirmation(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            VerificationResult vr =  tv.checkVerification(user.getPhoneNumber(), code);
            System.out.println("User number on confirmation: "+user.getPhoneNumber());
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
            User loggedUser = userService.authenticateUser(user.getEmail(), user.getUserPassword());
            session.setAttribute("loggedUser", loggedUser);
            return mv;
        }
        else {
            response.sendRedirect(request.getContextPath()+"/signup");
        }
        return null;
    }
    
    */
}
