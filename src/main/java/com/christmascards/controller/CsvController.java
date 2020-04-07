/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import com.christmascards.domain.ReferredOccasion;
import com.christmascards.domain.User;
//import com.christmascards.domain.UserXFriend;
import com.christmascards.service.ReferredOccasionService;
import com.christmascards.service.UserService;
//import com.christmascards.service.UserXFriendService;
import com.christmascards.util.LoginVerification;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author DMNGZ
 */
@Controller
@MultipartConfig
public class CsvController extends HttpServlet {

    /*
    @Autowired
    UserXFriendService usXfService;
    
    @Autowired
    FriendService friendService;*/

    @Autowired
    ReferredOccasionService refService;

    @Autowired
    UserService userService;

    Boolean friendCreated;

    Logger l = Logger.getLogger("logger");


    @RequestMapping(value="/addByCSV", method=RequestMethod.POST)
    public void addCSV(@RequestParam("csv") String[][] csvMatrix,HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{        if(LoginVerification.sessionCheck(request)){
            User user = (User) request.getSession().getAttribute("loggedUser");

            ReferredOccasion referredOccasion = new ReferredOccasion();
            ReferredOccasion returnedOccasion = new ReferredOccasion();
            //ReferredOccasion savedFriendCata = null;
            //Friend friend = new Friend();

            int i,j=0,insertCounter=6,totalData=csvMatrix.length;
            String temp=null,date=null;
                //It is a Matrix String[][], we`ll read from the second line onwards, the data itself
                for(i=6;i<totalData;i++){
                    //we`ll only care about their position from the 6 parameters
                    j=i%6;
                    temp=csvMatrix[i][0];
                    //Removing [" from every first name
                    if(j==0) temp = csvMatrix[i][0].substring(2);

                    //Removing "] or "]] from the occasion
                    if(j==5){
                        if(i==csvMatrix.length-1)temp=csvMatrix[i][0].substring(0, csvMatrix[i][0].length()-3); // if it is the very last occasion we need to remove "]]
                        else temp=csvMatrix[i][0].substring(0, csvMatrix[i][0].length()-2); // if it is a regular occasion we remove "]
                    }

                    switch(j){
                        case 0: referredOccasion.setFriendFirstName(temp);
                                break;
                        case 1:referredOccasion.setFriendLastName(temp);
                                break;
                        case 2:referredOccasion.setAddressLine1(temp);
                                break;
                        case 3:referredOccasion.setEmail(temp);
                                break;
                        case 4:date=temp;
                                break;        
                        case 5: referredOccasion.setOccasion(temp);
                                referredOccasion.setUser(user);

                                returnedOccasion = refService.addnewUserReferredOccasion(referredOccasion, date, Boolean.TRUE);


                                if(returnedOccasion!=null){
                                    friendCreated = true;
                                }
                                else{
                                    friendCreated = false;
                                }                                
                                  referredOccasion=new ReferredOccasion();
                                  returnedOccasion = new ReferredOccasion();

                                break; 
                    }
                }// for end                  

            response.sendRedirect(request.getContextPath()+"/dashboard"); 
        }
        else{
            response.sendRedirect(request.getContextPath()+"/login"); 
        }
    }



}