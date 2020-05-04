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
import java.util.ArrayList;
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
    SimpleDateFormat htmlDateFmt = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    
    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            ModelAndView mv = new ModelAndView("dashboard");
            String sorting = (String) request.getParameter("sorting");
            User user = (User) request.getSession().getAttribute("loggedUser");
            String searchWord = (String) request.getParameter("searchWord");
            System.out.println("SearchWord: "+searchWord);
            Page<ReferredOccasion> usersReferred = refService.getUsersReferredOccasions(user, currentPage, searchWord, sorting);           
            mv.addAllObjects(PaginAndSorting.dashboardPagingAndSorting(usersReferred,request,searchWord, sorting));
            mv.addObject("totalPages",usersReferred.getTotalPages());
            List<ReferredOccasion> referrals = new ArrayList();
            for(ReferredOccasion referral:usersReferred.getContent()){
                Calendar nextVerification = referral.getLastEmailDate();
                System.out.println("Last Email Date: " + nextVerification.getTime());
                String frequency = referral.getEmailFrequency();
                if(!referral.getInfoHasBeingFilled()){
                    if(frequency.equals("daily")){
                        nextVerification.add(Calendar.DATE, 1);
                    }
                    else if(frequency.equals("weekly")){
                        nextVerification.add(Calendar.DATE, 7);
                    }
                    else if(frequency.equals("monthly")){
                        nextVerification.add(Calendar.MONTH, 1);
                    }
                }
                referral.setNextVerification(nextVerification);
                referrals.add(referral);
            }
            mv.addObject("referrals",referrals);
            mv.addObject("username", user.getFirstName()+" "+user.getLastName());
            currentPage = 0;
            if(referralCreated != null){
                if(referralCreated){
                    mv.addObject("isCreated", true);
                }
                else{
                    mv.addObject("failedToCreate",true);
                }
                referralCreated = null;
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
            if(request.getParameter("userCreated")!=null){
                mv.addObject("userCreated", true);
            }
            mv.addObject("occasionFilter", searchWord);
            mv.addObject("occasionSorting", sorting);
            System.out.println("sorting: "+sorting);
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
    public void addFriend(HttpServletRequest request, HttpServletResponse response, @RequestParam("firstName") String firstName, 
            @RequestParam(value = "lastName", required = false) String lastName, @RequestParam(value = "country", required = false) String country, 
            @RequestParam(value = "state", required = false) String state, @RequestParam(value = "city", required = false) String city, 
            @RequestParam(value = "zipCode", required = false) String zipCode, @RequestParam("email") String email, 
            @RequestParam(value = "occasionDate", required = false) String date, @RequestParam("occasion") String occasion, 
            @RequestParam(value = "address", required = false) String address, @RequestParam("sendEmail") String sendEmail, 
            @RequestParam(value="emailFrequency" ,required = false) String emailFrequency, @RequestParam("timeZone") String timeZoneStr) throws ParseException, IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            
            ReferredOccasion referredOccasion = new ReferredOccasion();
            referredOccasion.setFriendFirstName(firstName);
            referredOccasion.setFriendLastName(lastName);
            referredOccasion.setEmail(email);
            referredOccasion.setAddressLine1(address);
            referredOccasion.setCountry(country);
            referredOccasion.setState(state);
            referredOccasion.setCity(city);
            referredOccasion.setZipCode(zipCode);
            referredOccasion.setOccasion(occasion);
            referredOccasion.setUser(user);
            referredOccasion.setLastEditedBy("Me");
            referredOccasion.setEmailFrequency(emailFrequency);
            if(user.getTimeZone()==null){
                int timeZone = Integer.parseInt(timeZoneStr);
                if (timeZone >= 0) {
                    timeZoneStr = "+" + timeZone;
                }
                user.setTimeZone("GMT" + timeZoneStr);
                TimeZone tz = TimeZone.getTimeZone("GMT" + timeZoneStr);
                userService.save(user);
            }
            referredOccasion.setLastEditedDate(Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())).getTime());  
            /*System.out.println("Current Calendar day by TimeZone" + Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())).getTime());
            System.out.println("Current last edited date: " + referredOccasion.getLastEditedDate().getTime().getDate());
            System.out.println("Current last edited time: " + referredOccasion.getLastEditedDate().getTime().getHours());*/
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
            refOccasion.setLastEditedDate(Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())).getTime());
            System.out.println("-------------I'm on referral 2-------------------");
            refService.saveReferedOccasion(refOccasion);
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
    
    @RequestMapping(value="/update/referral/input", method=RequestMethod.POST)
    public ModelAndView updateReferal(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("referredOccasionId") Integer referredOccasionId, @RequestParam("firstName")String firstName,
            @RequestParam(value = "lastName", required = false)String lastName, @RequestParam("emailAddress")String emailAddress,
            @RequestParam("occasion") String occasion, @RequestParam("occasionDate") String occasionDate,
            @RequestParam("country") String country, @RequestParam("state") String state, @RequestParam("city") String city,
            @RequestParam("addressLine1") String addressLine1, @RequestParam(value = "addressLine2", required = false) String addressLine2,
            @RequestParam("zipCode") String zipCode) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            ReferredOccasion refOccasion = refService.findReferedOccasion(referredOccasionId);
            refOccasion.setFriendFirstName(firstName);
            refOccasion.setFriendLastName(lastName);
            refOccasion.setEmail(state);
            refOccasion.setOccasion(occasion);
            if(occasionDate != null){
                try{
                    Date date = format.parse(occasionDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    refOccasion.setOccasionDate(cal);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            refOccasion.setCountry(country);
            refOccasion.setState(state);
            refOccasion.setCity(city);
            refOccasion.setAddressLine1(addressLine1);
            refOccasion.setAddressLine2(addressLine2);
            refOccasion.setZipCode(zipCode);
            refOccasion.setLastEditedBy("Me");
            User user = (User) request.getSession().getAttribute("loggedUser");
            refOccasion.setLastEditedDate(Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone())).getTime());
            System.out.println("---------------I'm on referral 3 ----------------------");
            refService.saveReferedOccasion(refOccasion);
            response.sendRedirect(request.getContextPath()+"/dashboard?userCreated=true");
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
    

