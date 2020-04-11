/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.christmascards.repository.*;
import com.christmascards.domain.*;
import com.christmascards.util.EmailSender;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author HP PC
 */
@Service
public class ReferredOccasionService {
    
    @Autowired
    ReferredOccasionRepository ror;
    
    @Autowired
    UserService us;
    
    Integer PAGESIZE = 10;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String referalUrl = "https://christmas-card-app2.herokuapp.com/referral?id=";
    ArrayList<String> personalizInitialPrameters = new ArrayList(Arrays.asList("Sender_Name","Sender_Address","Sender_City","Sender_State","Sender_Zip"));
    ArrayList<String> personalizInitialValues = new ArrayList(Arrays.asList("ChristmasCardApp","Specific Address","City","State","Zip Code"));
    /* Hans API*/
    String referralMessageTemplateId = "d-8b7f245fed484c6d866480d80578555a";
    /* Edwin API*/
    //String referralMessageTemplateId = "d-6f962eb4504e47c28c749af83061b2e4";
    
    public Page<ReferredOccasion> getUsersReferredOccasions(User user, String dateRange, Integer page){
        Calendar dateStart = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.clear(Calendar.MINUTE);
        dateStart.clear(Calendar.SECOND);
        dateStart.clear(Calendar.MILLISECOND);
        if(dateRange == null){
            return ror.findByUserAndIsDeletedOrderByReferredDateDescReferredOccasionIdDesc(user, Boolean.FALSE, new PageRequest(page, PAGESIZE));
        }
        if(dateRange.equals("monthly")){
            dateStart.set(Calendar.DAY_OF_MONTH, 1);
        }
        else if(dateRange.equals("weekly")){
            dateStart.set(Calendar.DAY_OF_WEEK, dateEnd.getFirstDayOfWeek());
        }
        else{
            return ror.findByUserAndIsDeletedOrderByReferredDateDescReferredOccasionIdDesc(user, Boolean.FALSE, new PageRequest(page, PAGESIZE));
        }
        return ror.findByUserAndIsDeletedAndReferredDateBetweenOrderByReferredDateDescReferredOccasionIdDesc(user, Boolean.FALSE, dateStart, dateEnd, new PageRequest(page, PAGESIZE));
    }
    
   public ReferredOccasion addnewUserReferredOccasion(ReferredOccasion referredOccasion, String occasionDate, Boolean emailRequested) throws ParseException, IOException{
        Calendar currentDate = Calendar.getInstance();
        if(occasionDate != null){
            try{
                Date date = format.parse(occasionDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                referredOccasion.setOccasionDate(cal);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        referredOccasion.setReferredDate(currentDate);
        referredOccasion.setInfoHasBeingFilled(Boolean.FALSE);
        referredOccasion.setIsDeleted(Boolean.FALSE);
        String refToken = "";
        int i = 0;
        while(i<100){ 
            Random random = ThreadLocalRandom.current();
            byte[] r = new byte[32];
            random.nextBytes(r);
            refToken = Base64.getUrlEncoder().encodeToString(r);
            if(refToken.length()>120){
                refToken = refToken.substring(0, 119);
            }
            if(ror.findFirstByReferrenceToken(refToken)==null){
                break;
            }
            i++;
        }
        referredOccasion.setReferrenceToken(refToken);
        
        ArrayList<String> personalizationParameters = new ArrayList();
        personalizationParameters.addAll(personalizInitialPrameters);
        personalizationParameters.add("User_Name");
        personalizationParameters.add("Friend_Name");
        personalizationParameters.add("Referal_URL");
        personalizationParameters.add("Occasion");

        User user = referredOccasion.getUser();
        ArrayList<String> personalizationValues = new ArrayList();
        personalizationValues.addAll(personalizInitialValues);
        personalizationValues.add(user.getFirstName() + " " + user.getLastName());
        personalizationValues.add(referredOccasion.getFriendFirstName());
        personalizationValues.add(referalUrl+refToken);
        personalizationValues.add(referredOccasion.getOccasion());
        String response = "";
       if(emailRequested!=null){
           if(emailRequested){
            response = EmailSender.sendEmail(referredOccasion.getEmail(), referredOccasion.getUser().getEmail(), referralMessageTemplateId, personalizationParameters,personalizationValues);
           }
       }
       if(response.equals("202")){
        referredOccasion.setLastEmailDate(currentDate);
       }
       else{
           referredOccasion.setEmailCanBeResent(Boolean.TRUE);
       }
        return ror.save(referredOccasion);
   }
   
   public ReferredOccasion addnewUserReferredOccasion(ReferredOccasion referredOccasion,  Boolean emailRequested) throws ParseException, IOException{
        Calendar currentDate = Calendar.getInstance();

        referredOccasion.setReferredDate(currentDate);
        referredOccasion.setInfoHasBeingFilled(Boolean.FALSE);
        referredOccasion.setIsDeleted(Boolean.FALSE);
        String refToken = "";
        int i = 0;
        while(i<100){ 
            Random random = ThreadLocalRandom.current();
            byte[] r = new byte[32];
            random.nextBytes(r);
            refToken = Base64.getUrlEncoder().encodeToString(r);
            if(refToken.length()>120){
                refToken = refToken.substring(0, 119);
            }
            if(ror.findFirstByReferrenceToken(refToken)==null){
                break;
            }
            i++;
        }
        referredOccasion.setReferrenceToken(refToken);
        
        ArrayList<String> personalizationParameters = new ArrayList();
        personalizationParameters.addAll(personalizInitialPrameters);
        personalizationParameters.add("User_Name");
        personalizationParameters.add("Friend_Name");
        personalizationParameters.add("Referal_URL");
        personalizationParameters.add("Occasion");

        User user = referredOccasion.getUser();
        ArrayList<String> personalizationValues = new ArrayList();
        personalizationValues.addAll(personalizInitialValues);
        personalizationValues.add(user.getFirstName() + " " + user.getLastName());
        personalizationValues.add(referredOccasion.getFriendFirstName());
        personalizationValues.add(referalUrl+refToken);
        personalizationValues.add(referredOccasion.getOccasion());
        String response = "";
       if(emailRequested!=null){
           if(emailRequested){
            response = EmailSender.sendEmail(referredOccasion.getEmail(), referredOccasion.getUser().getEmail(), referralMessageTemplateId, personalizationParameters,personalizationValues);
           }
       }
       if(response.equals("202")){
        referredOccasion.setLastEmailDate(currentDate);
       }
       else{
           referredOccasion.setEmailCanBeResent(Boolean.TRUE);
       }
        return ror.save(referredOccasion);
   }
   
   public void sendRemindingEmail(Integer referredOccasionId) throws IOException{
       Calendar currentDate = Calendar.getInstance();
       ReferredOccasion referredOccasion = ror.findOne(referredOccasionId);
       ArrayList<String> personalizationParameters = new ArrayList();
        personalizationParameters.addAll(personalizInitialPrameters);
        personalizationParameters.add("User_Name");
        personalizationParameters.add("Friend_Name");
        personalizationParameters.add("Referal_URL");
        personalizationParameters.add("Occasion");

        User user = referredOccasion.getUser();
        ArrayList<String> personalizationValues = new ArrayList();
        personalizationValues.addAll(personalizInitialValues);
        personalizationValues.add(user.getFirstName() + user.getLastName());
        personalizationValues.add(referredOccasion.getFriendFirstName());
        personalizationValues.add(referalUrl+referredOccasion.getReferrenceToken());
        personalizationValues.add(referredOccasion.getOccasion());
       String response = "";
       
        response = EmailSender.sendEmail(referredOccasion.getEmail(), referredOccasion.getUser().getEmail(), referralMessageTemplateId, personalizationParameters,personalizationValues);
        if(response.equals("202")){
            referredOccasion.setLastEmailDate(currentDate);
        }
        else{
            referredOccasion.setEmailCanBeResent(Boolean.TRUE);
        }
        ror.save(referredOccasion);
   }
   
   public boolean referalHasAccount(ReferredOccasion ref){
       
       return us.checkUserEmail(ref.getEmail());
   }
   
   public ReferredOccasion findReferalDetail(String referralCode){
       return ror.findFirstByReferrenceToken(referralCode);
   }
   
   public ReferredOccasion findReferedOccasion(Integer id){
       return ror.findOne(id);
   }
   
   public ReferredOccasion saveReferedOccasion(ReferredOccasion refOccasion){
       return ror.save(refOccasion);
   }
   
}
