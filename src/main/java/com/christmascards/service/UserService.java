/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.service;

import com.christmascards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.christmascards.domain.User;
import com.christmascards.util.EmailSender;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author HP PC
 */

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;
    
    
    //Saves an user on the DB after confirmation of phone number has been completed and sends an email to desired email address
    @Transactional
    public User registerUser(final User user) throws IOException{
        User userResult = userRepo.saveAndFlush(user);
        String message = "An account has just been registered \n "+
        "First Name: "+user.getFirstName()+"\n"+
        "Last Name: "+user.getLastName()+"\n"+
        "Email: "+user.getEmail()+"\n";
        EmailSender.sendEmail("emailtohans@gmail.com", message, "Account created on Christmas card App", "christmascards254@gmail.com", System.getenv("EMAIL_PASSWORD"));
        
        return userResult;
    }
    
    //Checks if phone number and email have already beign used, which if true would mean that the user alreeady has an account
    //"none" means no results were found for both, "both" means both email and phone number were found on an already existing account
    //"phone" means phone number was found on an already existing account, "email" means email was found on an already existing account
    @Transactional
    public String checkUserEmailAndPhone(String email, String phoneNumber){
        List<User> searchByEmail = userRepo.findByEmail(email);
        List<User> searchByPhone = userRepo.findByPhoneNumber(phoneNumber);
        String response = "none";
        
        
        if(searchByEmail!=null){
            if(!searchByEmail.isEmpty()){
                response = "email";
            }
        }
        if(searchByPhone!=null){
            if(!searchByPhone.isEmpty()){
                if(response.equals("email")){
                    response="phone";
                }
                else{
                    response="both";
                }
            }
        }
        return response;
    }
    
}
