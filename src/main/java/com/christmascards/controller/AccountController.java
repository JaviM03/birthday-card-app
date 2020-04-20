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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
    SimpleDateFormat htmlDateFmt = new SimpleDateFormat("yyyy-MM-dd");
    
    
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
            if(request.getParameter("friendEdited")!=null){
                mv.addObject("friendEdited", true);
            }
            if(request.getParameter("referralDeleted")!=null){
                mv.addObject("referralDeleted", true);
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
            @RequestParam("sendEmail") String sendEmail, @RequestParam(value = "timeZone") String timeZoneStr) throws ParseException, IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            
            ReferredOccasion referredOccasion = new ReferredOccasion();
            referredOccasion.setFriendFirstName(firstName);
            referredOccasion.setFriendLastName(lastName);
            referredOccasion.setEmail(email);
            referredOccasion.setAddressLine1(address);
            referredOccasion.setOccasion(occasion);
            referredOccasion.setUser(user);
            referredOccasion.setLastEditedBy("Me");
            if(user.getTimeZone()==null){
                int timeZone = Integer.parseInt(timeZoneStr);
                if (timeZone >= 0) {
                    timeZoneStr = "+" + timeZone;
                }
                user.setTimeZone("GMT" + timeZoneStr);
                TimeZone tz = TimeZone.getTimeZone("GMT" + timeZoneStr);
                System.out.println(tz.getDisplayName());
                System.out.println(tz.getRawOffset());
            }
            referredOccasion.setLastEditedDate(Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())));
            System.out.println(Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())).getTime());
            userService.save(user);
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
    
    //Method for accepting incomming referal update information
    @RequestMapping(value="/referral/update", method=RequestMethod.POST)
    public void updateReferal(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="firstName", required = false) String firstName, 
            @RequestParam(name = "lastName", required = false) String lastName, @RequestParam(name = "email", required = false) String email, 
            @RequestParam(name = "occasionDate", required = false) String date, @RequestParam(name = "occasion", required = false) String occasion, 
            @RequestParam(name = "address", required = false) String address, @RequestParam(name = "referredOccasionId")Integer id) throws ParseException, IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            ReferredOccasion refOccasion = refService.findReferedOccasion(id);
            if(firstName!=null){
                refOccasion.setFriendFirstName(firstName);
            }
            if(lastName!=null){
                refOccasion.setFriendLastName(lastName);
            }
            if(email!=null){
                refOccasion.setEmail(email);
            }
            if(date!=null){
                Date jDate = htmlDateFmt.parse(date);
                Calendar cal = Calendar.getInstance();
                 cal.setTime(jDate);
                refOccasion.setOccasionDate(cal);
            }
            if(occasion!=null){
                refOccasion.setOccasion(occasion);
            }
            if(address!=null){
                refOccasion.setAddressLine1(address);
            }
            refOccasion.setLastEditedBy("Me");
            refOccasion.setLastEditedDate(Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())));
            response.sendRedirect(request.getContextPath()+"/dashboard?friendEdited=true"); 
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
    }
    
    @RequestMapping(value="/update/referral", method=RequestMethod.POST)
    public ModelAndView updateReferal(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("referredOccasionId") Integer referredOccasionId) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            ModelAndView mv = new ModelAndView("user/edit-referral");
            mv.addObject("username", user.getFirstName()+" "+user.getLastName());
            ReferredOccasion ref = refService.findReferedOccasion(referredOccasionId);
            mv.addObject("refOccasion",ref);
            return mv;
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
        return null;
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
        response.sendRedirect(request.getContextPath()+"/dashboard?referralDeteled=true");
    }
  
    }
    

