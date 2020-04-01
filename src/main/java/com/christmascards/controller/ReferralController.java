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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author HP PC
 */
@Controller
public class ReferralController {
    
    @Autowired
    ReferredOccasionService refOccService;
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    @RequestMapping(value="/referral")
    public ModelAndView referralPoint(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="id")String id) throws IOException{
        
        if(LoginVerification.sessionCheck(request)){
            ReferredOccasion refOccasion = refOccService.findReferalDetail(id);
            if(refOccasion!=null){
                if(refOccasion.getInfoHasBeingFilled()){
                 response.sendRedirect(request.getContextPath()+"/login?referred=true");   
                }
                else{
                    MainController.REFERREDCODE = id;
                    return new ModelAndView("referral/referralForm");
                }
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login?referralCode=false");
            }
        }
        else{
        if(refOccService.findReferalDetail(id)!=null){
                MainController.REFERREDCODE = id;
                return new ModelAndView("referral/referralForm");
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
        
        if(LoginVerification.sessionCheck(request)){
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
                refOccService.saveReferedOccasion(refOccasion);
                MainController.REFERREDCODE = null;
                return new ModelAndView("referral/referralSuccess");
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login");
            }
            
        }
        else{
           response.sendRedirect(request.getContextPath()+"/login");
        }
        return null;
    }
}
