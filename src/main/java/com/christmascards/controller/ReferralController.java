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
            if(refOccService.findReferalDetail(id)!=null){
                MainController.REFERREDCODE = id;
                response.sendRedirect(request.getContextPath()+"/referral/info");
            }
            else{
                response.sendRedirect(request.getContextPath()+"/login?referralCode=false");
            }
        }
        else{
        if(refOccService.findReferalDetail(id)!=null){
                MainController.REFERREDCODE = id;
                System.out.println("Refered Code = " + MainController.REFERREDCODE);
                return new ModelAndView("referral/referralLandingPage");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/");
        }
        }
        return null;
    }
    
    @RequestMapping(value="/referral/info")
    public ModelAndView referralForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        if(LoginVerification.sessionCheck(request)){
           
            if(MainController.REFERREDCODE!=null){
                ReferredOccasion refOccasion = refOccService.findReferalDetail(MainController.REFERREDCODE);
                ModelAndView mv = new ModelAndView("referral/referralForm");
                mv.addObject("refOccasion",refOccasion);
                return mv;
            }
            else{
                response.sendRedirect(request.getContextPath()+"/dashboard");
            }
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login");
        }
        return null;
    }
    
    @RequestMapping(value="/referred-information")
    public ModelAndView referralInformation(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="referredOccasionId") Integer id,
            @RequestParam(name="occasionDate") String occasionDate, @RequestParam(name="address") String address) throws IOException, ParseException{
        if(LoginVerification.sessionCheck(request)){
            if(MainController.REFERREDCODE!=null){
                ReferredOccasion refOccasion = refOccService.findReferedOccasion(id);
                refOccasion.setAddress(address);
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
