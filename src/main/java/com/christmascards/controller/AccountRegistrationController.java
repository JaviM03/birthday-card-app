/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.domain.User;
import com.christmascards.service.UserService;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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
    
    @RequestMapping(value="/signup")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("account-registration/register");
        return mv;
    }
    
    @RequestMapping(value="/user-signup", method = RequestMethod.POST)
    public ModelAndView signUpUser(HttpServletRequest request, @RequestParam("firstName") String firstName, @RequestParam("lastName") String LastName,
            @RequestParam("email") String email, @RequestParam("phoneNumber") String number, @RequestParam("password") String password
    ){
        String emailAndPhoneConfirm = userService.checkUserEmailAndPhone(email, number);
        l.info(emailAndPhoneConfirm);
        if(emailAndPhoneConfirm.equals("none")){
            User user = new User();
            user.setFirstName(firstName);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(LastName);
            user.setUserPassword(password);
            ModelAndView mv = new ModelAndView("account-registration/phoneConfirmation");
            mv.addObject("user", user);
            return mv;
        }
        else {
            return new ModelAndView("account-registration/register");
        }
    }
}
