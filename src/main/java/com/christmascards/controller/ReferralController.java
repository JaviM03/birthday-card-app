/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.service.ReferredOccasionService;
import com.christmascards.util.LoginVerification;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.christmascards.domain.*;
import com.christmascards.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author HP PC
 */
@Controller
public class ReferralController {
    
    @Autowired
    ReferredOccasionService refOccService;
    
    @Autowired
    UserService us;
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    @RequestMapping(value="/referral")
    public ModelAndView referralPoint(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="id")String id) throws IOException{
        ReferredOccasion refOccasion = refOccService.findReferalDetail(id);
        if(LoginVerification.sessionCheck(request)){
            
            if(refOccasion!=null){
                ModelAndView mv = new ModelAndView("referral/referralForm");
                if(refOccasion.getInfoHasBeingFilled()){
                 mv.addObject("infoHasBeingFilled", true);
                }
                    MainController.REFERREDCODE = id;
                    mv.addObject("refOccasion", refOccasion);
                    return mv;               
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login?referralCode=false");
            }
        }
        else{
        if(refOccasion!=null){
                MainController.REFERREDCODE = id;
                ModelAndView mv = new ModelAndView("referral/referralForm");
                mv.addObject("refOccasion", refOccasion);
                return mv;
        }
        else{
            response.sendRedirect(request.getContextPath()+"/");
        }
        }
        return null;
    }
    
    
    @RequestMapping(value="/referred-information")
    public ModelAndView referralInformation(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="referredOccasionId") Integer id,
            @RequestParam(name="occasionDate") String occasionDate, @RequestParam(name="addressLine1") String addressLine1,
            @RequestParam(name="addressLine2", required = false) String addressLine2, @RequestParam(name="zipCode") String zipCode,
            @RequestParam(name="country") String country, @RequestParam(name="state") String state,
            @RequestParam(name="city") String city) throws IOException, ParseException{
        
            if(MainController.REFERREDCODE!=null){
                ReferredOccasion refOccasion = refOccService.findReferedOccasion(id);
                refOccasion.setAddressLine1(addressLine1);
                refOccasion.setAddressLine2(addressLine2);
                refOccasion.setZipCode(zipCode);
                refOccasion.setCity(city);
                refOccasion.setCountry(country);
                refOccasion.setState(state);
                Date date= format.parse(occasionDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                refOccasion.setOccasionDate(cal);
                refOccasion.setInfoHasBeingFilled(Boolean.TRUE);
                refOccasion.setLastEditedBy(refOccasion.getFriendFirstName());
                refOccasion.setLastEditedDate(Calendar.getInstance(TimeZone.getTimeZone(refOccasion.getUser().getTimeZone())));
                Calendar currentDate = Calendar.getInstance();
                refOccService.saveReferedOccasion(refOccasion);
                us.sendUserReferredConfirmationEmail(refOccasion);
                MainController.REFERREDCODE = null;
                if(refOccService.referalHasAccount(refOccasion)){
                    return new ModelAndView("referral/referralSuccessWithExistingAccount");
                }
                else{
                    ModelAndView mv = new ModelAndView("referral/referralSuccess");
                    mv.addObject("referredOccasionId",id);
                    return mv;
                }
            }
            else{
                response.sendRedirect(request.getContextPath());
            }
            

        return null;
    }
    
    
    @RequestMapping(value="/referral/register", method = RequestMethod.POST)
    public ModelAndView referralRegisterPOST(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="id")Integer id,
            @RequestParam(name="password") String password) throws IOException{
        ReferredOccasion refOcc = refOccService.findReferedOccasion(id);
        if(refOcc == null){
            response.sendRedirect(request.getContextPath());
            return null;
        }
        User user = new User();
        user.setEmail(refOcc.getEmail());
        user.setFirstName(refOcc.getFriendFirstName());
        user.setLastName(refOcc.getFriendLastName());
        user.setUserPassword(password);
        user = us.registerUser(user);
        request.getSession().setAttribute("loggedUser", user);
        return new ModelAndView("account-registration/successfulAccCreation");
    }
    
    @RequestMapping(value="/referral/register", method = RequestMethod.GET)
    public ModelAndView referralRegisterGET(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="id")Integer id) throws IOException{
        ModelAndView mv = new ModelAndView("referral/referralSignUp");
        System.out.println("");
        mv.addObject("referredOccasionId",id);
        return mv;
    }
    
    
}
