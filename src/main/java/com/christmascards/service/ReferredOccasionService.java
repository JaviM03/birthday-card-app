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
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    
    public Page<ReferredOccasion> getUsersReferredOccasions(User user, Integer page, String searchWord, String sorting){
        Sort sort = Sort.by("occasion");
        if(sorting != null){  
            if(sorting.equals("")){
                if(searchWord!=null){
                    return ror.findReferredOccasionByUserAndSearchWord(user.getUserId(), searchWord, PageRequest.of(page, PAGESIZE));
                }
                else{
                    return ror.findByUserAndIsDeletedOrderByReferredOccasionIdDesc(user, Boolean.FALSE, PageRequest.of(page, PAGESIZE));
                }
            }
            if(sorting.equals("asc")){
                sort = Sort.by("occasion").ascending();
            }   
            if(sorting.equals("desc")){
                sort = Sort.by("occasion").descending();
            }
            if(searchWord!=null){
                return ror.findReferredOccasionByUserAndSearchWordNoOrderBy(user.getUserId(), searchWord, PageRequest.of(page, PAGESIZE, sort));
            }
            else{
                return ror.findByUserAndIsDeleted(user, Boolean.FALSE, PageRequest.of(page, PAGESIZE, sort));
            }
        }
        else{
            if(searchWord!=null){
                return ror.findReferredOccasionByUserAndSearchWord(user.getUserId(), searchWord, PageRequest.of(page, PAGESIZE));
            }
            else{
                return ror.findByUserAndIsDeletedOrderByReferredOccasionIdDesc(user, Boolean.FALSE, PageRequest.of(page, PAGESIZE));
            }
        }       
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
   
   public List<ReferredOccasion> addNewReferedOccasions(List<ReferredOccasion> referedOccasions){       
       List<Object> returnValue = new ArrayList();
       Iterable<ReferredOccasion> iterable = ror.save(referedOccasions);
       returnValue =  StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
       return (List<ReferredOccasion>) ror.save(referedOccasions);
   }
   
   public void sendRemindingEmail(Integer referredOccasionId) throws IOException{
       Calendar currentDate = Calendar.getInstance();
       ReferredOccasion referredOccasion = ror.findById(referredOccasionId).get();
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
       return ror.findById(id).get();
   }
   
   public ReferredOccasion saveReferedOccasion(ReferredOccasion refOccasion){
       return ror.save(refOccasion);
   }
   
   public Iterable<ReferredOccasion> saveReferredOccasions(ArrayList<ReferredOccasion> refOccasions) throws IOException{
       Calendar currentDate = Calendar.getInstance();
       
       ArrayList<String> personalizationParameters = new ArrayList();
        personalizationParameters.addAll(personalizInitialPrameters);
        personalizationParameters.add("User_Name");
        personalizationParameters.add("Friend_Name");
        personalizationParameters.add("Referal_URL");
        personalizationParameters.add("Occasion");       
       
       for(int i=0; i < refOccasions.size();i++){
           ReferredOccasion referredOccasion = refOccasions.get(i);
        referredOccasion.setReferredDate(currentDate);
        referredOccasion.setInfoHasBeingFilled(Boolean.FALSE);
        referredOccasion.setIsDeleted(Boolean.FALSE);
        String refToken = "";
        int j = 0;
        while(j<100){ 
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
            j++;
        }
        referredOccasion.setReferrenceToken(refToken);
        refOccasions.set(i, referredOccasion);
        
        User user = referredOccasion.getUser();
        ArrayList<String> personalizationValues = new ArrayList();
        personalizationValues.addAll(personalizInitialValues);
        personalizationValues.add(user.getFirstName() + " " + user.getLastName());
        personalizationValues.add(referredOccasion.getFriendFirstName());
        personalizationValues.add(referalUrl+refToken);
        personalizationValues.add(referredOccasion.getOccasion());
        String response = EmailSender.sendEmail(referredOccasion.getEmail(), referredOccasion.getUser().getEmail(), referralMessageTemplateId, personalizationParameters,personalizationValues);
       }
      return ror.save(refOccasions);
   }
   
   public void checkIfReferedOccasionEmailCanBeSent(){
       
       Iterator<ReferredOccasion> refOccasions = ror.findAll().iterator();
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.DAY_OF_YEAR, -2);
       calendar.set(Calendar.HOUR_OF_DAY, 0);
       calendar.set(Calendar.MINUTE, 0);
       calendar.set(Calendar.SECOND, 0);      
       calendar.set(Calendar.MILLISECOND, 0);
       while(refOccasions.hasNext()){           
           ReferredOccasion refOccasion = refOccasions.next();
           if(refOccasion.getEmailCanBeResent()){continue;}
           refOccasion.getLastEditedDate().compareTo(calendar);
           if(refOccasion.getLastEmailDate().compareTo(calendar) >= 0 ){
               refOccasion.setEmailCanBeResent(Boolean.TRUE);
               ror.save(refOccasion);
           }           
            //if info has not been filled
           if(!refOccasion.getInfoHasBeingFilled()){
                refOccasion.setLastEmailDate(sendReferredOccasionEmail(refOccasion.getUser().getUserId(),
                        refOccasion.getEmailFrequency(),refOccasion.getLastEmailDate(),calendar));
           }
           
       }
       
   }
   
   public Calendar sendReferredOccasionEmail(Integer userId, String freq,Calendar lastSent,Calendar calendar){
        Calendar compareDate = null;        
       
        //check daily reminder
        compareDate = lastSent;
        compareDate.add(Calendar.DAY_OF_MONTH,1);
        if(freq.equals("daily")  && (compareDate.compareTo(calendar) >= 0)){
               //sends email daily
               try  {
                  sendRemindingEmail(userId);  
                  lastSent=calendar;
                }
               catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
               
           }
        
        //check weekly reminder
        compareDate = null;
        compareDate = lastSent;
        compareDate.add(Calendar.WEEK_OF_YEAR,1);
        if(freq.equals("weekly") && (compareDate.compareTo(calendar) >= 0)){
            try  {
                  sendRemindingEmail(userId);  
                  lastSent=calendar;
                }
               catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            
        }
        
        
        //check monthly reminder
        compareDate = null;
        compareDate = lastSent;
        compareDate.add(Calendar.MONTH,1);
        if(freq.equals("monthly") && (compareDate.compareTo(calendar) >= 0)){
            try  {
                  sendRemindingEmail(userId);  
                  lastSent=calendar;
                }
            catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            
        }
        
        //otherwise, the last sent mail returns to normal
        return lastSent;
   }
   
}
