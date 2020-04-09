/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.service.UserService;
import com.christmascards.util.LoginVerification;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.christmascards.domain.*;
import com.christmascards.service.ReferredOccasionService;
import com.christmascards.util.PaginAndSorting;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
/**
 *
 * @author HP PC
 */

@Controller
public class AccountController {
    
    
    @Autowired
    UserService userService;
    
    
    @Autowired
    ReferredOccasionService refService;
    
    Boolean referralCreated;
    
    Integer currentPage = 0;
    Boolean gotRequestedNextPage;
    String dateRange = "weekly";
    
    
    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            ModelAndView mv = new ModelAndView("dashboard");
            //if(request.getHeader("Referer").equals("")){}
            if(request.getParameter("dateRange")!=null){
                dateRange = request.getParameter("dateRange");
                response.sendRedirect(request.getContextPath()+"/dashboard");
                System.out.println("Entre al if de dateRange");
                if(request.getParameter("page")!=null){
                    currentPage=Integer.parseInt(request.getParameter("page"));
                    System.out.println("Entre al if de page");
                }
                return null;
            }   
            
            if(dateRange!=null){
                mv.addObject("dateRange", dateRange);
            }
            String mDateRange = dateRange;
            mv.addObject("dateRange", mDateRange);
            System.out.println("Current Page: "+currentPage);
            User user = (User) request.getSession().getAttribute("loggedUser");
            Page<ReferredOccasion> usersReferred = refService.getUsersReferredOccasions(user,(dateRange==null?"weekly":dateRange),currentPage);
            mv.addAllObjects(PaginAndSorting.dashboardPagingAndSorting(usersReferred,request,dateRange));
            mv.addObject("totalPages",usersReferred.getTotalPages());
            mv.addObject("referrals",usersReferred.getContent());
            mv.addObject("username", user.getFirstName()+" "+user.getLastName());
            currentPage = 0;
            dateRange = "weekly";
            if(referralCreated != null){
                if(referralCreated){
                    mv.addObject("isCreated", true);
                }
                else{
                    mv.addObject("failedToCreate",true);
                }
                referralCreated = null;
            }
            if(dateRange!=null){
                mv.addObject("");
            }
            else{
                mv.addObject("dateRange","week");
            }
            if(request.getParameter("emailSent")!=null){
                mv.addObject("emailSent",true);
            }
            return mv;
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login");
            return null;
        }
    }
    
    @RequestMapping(value="/friends", method=RequestMethod.GET)
    public String getFriends(HttpServletRequest request, HttpServletResponse response, int page, int dateRange){
        
        return "";
    }
    
    @RequestMapping(value="/referral/add", method=RequestMethod.POST)
    public void addFriend(HttpServletRequest request, HttpServletResponse response, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, @RequestParam("occasionDate") String date, @RequestParam("occasion") String occasion, @RequestParam("address") String address,
            @RequestParam("sendEmail") String sendEmail) throws ParseException, IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            
            ReferredOccasion referredOccasion = new ReferredOccasion();
            referredOccasion.setFriendFirstName(firstName);
            referredOccasion.setFriendLastName(lastName);
            referredOccasion.setEmail(email);
            referredOccasion.setAddressLine1(address);
            referredOccasion.setOccasion(occasion);
            referredOccasion.setUser(user);
            
            System.out.println("Send Email is: "+ sendEmail);
            
            ReferredOccasion returnedOccasion = refService.addnewUserReferredOccasion(referredOccasion, date, Boolean.TRUE);
            if(returnedOccasion!=null){
                referralCreated = true;
            }
            else{
                referralCreated = false;
            }
       response.sendRedirect(request.getContextPath()+"/dashboard"); 
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
    }
    
    @RequestMapping(value="/sendEmail",method=RequestMethod.POST)
    public void resendEmailToReferral(HttpServletRequest request, HttpServletResponse response, @RequestParam("friendId") Integer userXFriendId) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            refService.sendRemindingEmail(userXFriendId);
            response.sendRedirect(request.getContextPath()+"/dashboard?emailSent=true");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.getSession().setAttribute("loggedUser", null);
        response.sendRedirect(request.getContextPath()+"/login");
    }
    
    @RequestMapping(value="/referral/delete", method = RequestMethod.POST)
    public void referralDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="id") Integer referredOccasionId) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            ReferredOccasion refOcc = refService.findReferedOccasion(referredOccasionId);
            if(user.getUserId().equals(refOcc.getUser().getUserId())){
                refOcc.setIsDeleted(true);
                refService.saveReferedOccasion(refOcc);
            }
        }
        response.sendRedirect(request.getContextPath()+"/dashboard");
    }
  
    }
    

