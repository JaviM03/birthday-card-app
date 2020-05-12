/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.service;

import com.christmascards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.christmascards.domain.*;
import com.christmascards.util.EmailSender;
import com.christmascards.util.PasswordUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    ArrayList<String> personalizInitialPrameters = new ArrayList(Arrays.asList("Sender_Name","Sender_Address","Sender_City","Sender_State","Sender_Zip"));
    ArrayList<String> personalizInitialValues = new ArrayList(Arrays.asList("ChristmasCardApp","Specific Address","City","State","Zip Code"));
    String senderEmail = "support@mydigitaladdressbook.com";
    /* Hans API */
    String welcomeMessageTemplateId = "d-7079eb579209441ea6148739f0d1a095";
    String referralConfirmationTemplateId = "d-fea95e67b03b46f5a987b76f92a2ddd9";
    /* Edwin API*/
    //String welcomeMessageTemplateId = "d-caa160ce04714770a9ee0d78be44f13a";
    
    //Saves an user on the DB after confirmation of phone number has been completed and sends an email to desired email address
    @Transactional
    public User registerUser(final User user) throws IOException{
        //Record the users password and hash it with auto-generated salt | Then save the user
        String salt = PasswordUtils.getSalt(30);
        String securePass = PasswordUtils.generateSecurePassword(user.getUserPassword(),salt);
        user.setUserPassword(securePass);
        user.setPassSalt(salt);
        User userResult = userRepo.saveAndFlush(user);
        
        //After User is registered we will send them a welcome email and we will send an email to the administration, to inform that a new account has being created
        String message = "An account has just been registered. <br>"+
        "First Name: "+user.getFirstName()+"<br>"+
        "Last Name: "+user.getLastName()+"<br>"+
        "Email: "+user.getEmail();
        //EmailSender.sendHTMLEmail("support@mydigitaladdressbook.com", "support@mydigitaladdressbook.com",  message, "Account created on Christmas card App");
        ArrayList<String> personalizationParameters = new ArrayList();
        ArrayList<String> personalizationValues = new ArrayList();
        personalizationParameters.add("User_Name");
        personalizationValues.add(user.getFirstName());
        personalizationParameters.add("Sender_Name");
        personalizationValues.add("ChristmasCard App");
        personalizationParameters.add("Sender_Address");
        personalizationValues.add("Pk Street Something Something");
        personalizationParameters.add("Sender_City");
        personalizationValues.add("Miami");
        personalizationParameters.add("Sender_State");
        personalizationValues.add("Florida");
        personalizationParameters.add("Sender_Zip");
        personalizationValues.add("4122");
        EmailSender.sendEmail(user.getEmail(), senderEmail, welcomeMessageTemplateId, personalizationParameters, personalizationValues);
        return userResult;
    }
    
    //Checks if phone number and email have already being used, which if true would mean that the user alreeady has an account
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
    //True means email was found on an existing account
    @Transactional
    public boolean checkUserEmail(String email){
        List<User> searchByEmail = userRepo.findByEmail(email);
        if(searchByEmail!=null){
            if(!searchByEmail.isEmpty()){
                return true;
            }
        }
        return false;
    }
    
    //Authenticate user using its email, password and DB saved password salt 
    @Transactional
    public User authenticateUser(String email, String password){
        User foundUser = userRepo.findFirstByEmail(email);
        if(foundUser == null){return null;}
        if (PasswordUtils.verifyUserPassword(password, foundUser.getUserPassword(), foundUser.getPassSalt())){
            return foundUser;
        }
        else {
            return null;
        }
    }
    
    public User save(User user){
        return userRepo.saveAndFlush(user);
    }
    
    public void sendUserReferredConfirmationEmail(ReferredOccasion refOcc) throws IOException{
        User user = refOcc.getUser();
       ArrayList<String> personalizationParameters = new ArrayList();
        personalizationParameters.addAll(personalizInitialPrameters);
        personalizationParameters.add("User_Name");
        personalizationParameters.add("Friend_Name");
        personalizationParameters.add("Occasion");
        
        ArrayList<String> personalizationValues = new ArrayList();
        personalizationValues.addAll(personalizInitialValues);
        personalizationValues.add(user.getFirstName());
        personalizationValues.add(refOcc.getFriendFirstName() + " " +refOcc.getFriendLastName());
        personalizationValues.add(refOcc.getOccasion());
        
        EmailSender.sendEmail(user.getEmail(), senderEmail, referralConfirmationTemplateId, personalizationParameters, personalizationValues);
   }
}
