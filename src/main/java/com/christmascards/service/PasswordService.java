/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.service;

import com.christmascards.domain.ReferredOccasion;
import com.christmascards.domain.User;
import com.christmascards.repository.UserRepository;
import com.christmascards.util.EmailSender;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordService {
    
    @Autowired
    UserRepository userRepo;
    
    ArrayList<String> personalizInitialPrameters = new ArrayList(Arrays.asList("Sender_Name","Sender_Address","Sender_City","Sender_State","Sender_Zip"));
    ArrayList<String> personalizInitialValues = new ArrayList(Arrays.asList("ChristmasCardApp","Specific Address","City","State","Zip Code"));
   
    
    String senderEmail = "support@mydigitaladdressbook.com";
    /* reset API */
    String resetPasswordMessageTemplateId = "d-3960a1e295bc4788973f9828ecb99539";
    
    public void sendResetPasswordEmail(User user,String appUrl) throws ParseException, IOException{
      
        ArrayList<String> personalizationParameters = new ArrayList();
        personalizationParameters.addAll(personalizInitialPrameters);
        personalizationParameters.add("User_Name");
        personalizationParameters.add("User_Email");
        personalizationParameters.add("Recover_Password_URL");

        ArrayList<String> personalizationValues = new ArrayList();
        personalizationValues.addAll(personalizInitialValues);
        personalizationValues.add(user.getFirstName() + " " + user.getLastName());
        personalizationValues.add(user.getEmail());
        personalizationValues.add(appUrl+"/reset?token="+user.getResetToken());
        
        String response = "";
        response = EmailSender.sendResetEmail(user.getEmail(), senderEmail, resetPasswordMessageTemplateId, personalizationParameters,personalizationValues);
       
    
    }
    
    @Transactional
	public Optional findUserByResetToken(String resetToken) {
		return userRepo.findUserByResetToken(resetToken);
	}
    
}
