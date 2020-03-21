/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.service.UserService;
import com.christmascards.service.UserXFriendService;
import com.christmascards.util.LoginVerification;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.christmascards.domain.*;
import com.christmascards.service.FriendService;
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
    UserXFriendService usXfService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    FriendService friendService;
    
    Boolean friendCreated;
    
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
            Page<UserXFriend> friends = usXfService.getUsersFriends(user,(dateRange==null?"weekly":dateRange),currentPage);
            mv.addAllObjects(PaginAndSorting.dashboardPagingAndSorting(friends,request,dateRange));
            mv.addObject("totalPages",friends.getTotalPages());
            mv.addObject("friends",friends.getContent());
            mv.addObject("username", user.getFirstName()+" "+user.getLastName());
            currentPage = 0;
            dateRange = "weekly";
            if(friendCreated != null){
                if(friendCreated){
                    mv.addObject("isCreated", true);
                }
                else{
                    mv.addObject("failedToCreate",true);
                }
                friendCreated = null;
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
    
    @RequestMapping(value="/addOccasion", method=RequestMethod.POST)
    public void addFriend(HttpServletRequest request, HttpServletResponse response, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, @RequestParam("occasionDate") String date, @RequestParam("occasion") String occasion, @RequestParam("address") String address) throws ParseException, IOException{
        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");
            UserXFriend friendCatalogue = new UserXFriend();
            Friend friend = new Friend();
            friend.setFirstName(firstName);
            friend.setLastName(lastName);
            friend.setEmail(email);
            friend.setAddressLine1(address);
            friend = friendService.saveFriend(friend);
            friendCatalogue.setOccasion(occasion);
            friendCatalogue.setFriend(friend);
            friendCatalogue.setUser(user);
            UserXFriend savedFriendCata = usXfService.addNewUserFriend(friendCatalogue, date);
            if(savedFriendCata!=null){
                friendCreated = true;
                usXfService.sendFriendEmail(friend);
            }
            else{
                friendCreated = false;
            }
       response.sendRedirect(request.getContextPath()+"/dashboard"); 
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
    }
    
    @RequestMapping(value="/sendEmail",method=RequestMethod.POST)
    public void sendClientEmail(HttpServletRequest request, HttpServletResponse response, @RequestParam("friendId") Integer userXFriendId) throws IOException{
        if(LoginVerification.sessionCheck(request)){
            usXfService.sendFriendRemindingEmail(userXFriendId);
            response.sendRedirect(request.getContextPath()+"/dashboard?emailSent=true");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
    }
    
    }
    

