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
    String referalUrl = "http://app.mydigitaladdressbook.com/referral?id=";
    ArrayList<String> personalizInitialPrameters = new ArrayList(Arrays.asList("Sender_Name","Sender_Address","Sender_City","Sender_State","Sender_Zip"));
    ArrayList<String> personalizInitialValues = new ArrayList(Arrays.asList("ChristmasCardApp","Specific Address","City","State","Zip Code"));
    /* Hans API*/
    String referralMessageTemplateId = "d-8b7f245fed484c6d866480d80578555a";
    /* Edwin API*/
    //String referralMessageTemplateId = "d-6f962eb4504e47c28c749af83061b2e4";
    
    public Page<ReferredOccasion> getUsersReferredOccasions(User user, Integer page, String searchWord, String sorting){
        if(searchWord!=null){
            if(searchWord.equals("") || searchWord.equals("null")){
                searchWord = null;
            }
        }
        Sort sort = Sort.by("occasion");
        if(sorting != null){  
            if(sorting.equals("")){
                if(searchWord!=null){
                    if(!searchWord.equals("null")){
                    return ror.findReferredOccasionByUserAndSearchWord(user.getUserId(), searchWord, PageRequest.of(page, PAGESIZE));
                    }
                    else{
                        return ror.findByUserAndIsDeletedOrderByReferredOccasionIdDesc(user, Boolean.FALSE, PageRequest.of(page, PAGESIZE));
                    }
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
                System.out.println("Search word not null");
                return ror.findReferredOccasionByUserAndSearchWordNoOrderBy(user.getUserId(), searchWord, PageRequest.of(page, PAGESIZE, sort));
            }
            else{
                return ror.findByUserAndIsDeleted(user, Boolean.FALSE, PageRequest.of(page, PAGESIZE, sort));
            }
        }
        else{
            if(searchWord!=null){
                if(!searchWord.equals("null")){
                    return ror.findReferredOccasionByUserAndSearchWord(user.getUserId(), searchWord, PageRequest.of(page, PAGESIZE));
                }
                else{
                return ror.findByUserAndIsDeletedOrderByReferredOccasionIdDesc(user, Boolean.FALSE, PageRequest.of(page, PAGESIZE));
            }
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
        
        while(referredOccasion.getFriendFirstName().endsWith(" ")){
            referredOccasion.setFriendFirstName(referredOccasion.getFriendFirstName().substring(0,referredOccasion.getFriendFirstName().lastIndexOf(" ")));
        }
        while(referredOccasion.getFriendLastName().endsWith(" ")){
            referredOccasion.setFriendLastName(referredOccasion.getFriendLastName().substring(0,referredOccasion.getFriendLastName().lastIndexOf(" ")));
        }
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
        referredOccasion.setEmailCanBeResent(Boolean.FALSE);
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
       System.out.println("Entered email can be sent method");
       Iterator<ReferredOccasion> refOccasions = ror.findAll().iterator();
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.DAY_OF_YEAR, -2);
       calendar.set(Calendar.HOUR_OF_DAY, 0);
       calendar.set(Calendar.MINUTE, 0);
       calendar.set(Calendar.SECOND, 0);      
       calendar.set(Calendar.MILLISECOND, 0);
       int i = 0;
       while(refOccasions.hasNext()){   
           ReferredOccasion refOccasion = refOccasions.next();
           i++;
          
           System.out.println("-----------------------------------------");
           if(!refOccasion.getInfoHasBeingFilled()){
               System.out.println("Entered info has not being filled");
               try{
                Calendar newLastEmailDate = sendReferredOccasionEmail(refOccasion.getUser().getUserId(),
                        refOccasion.getEmailFrequency(),refOccasion.getLastEmailDate()); 
                if(newLastEmailDate.equals(refOccasion.getLastEmailDate())){
                    refOccasion.setEmailCanBeResent(Boolean.FALSE);
                    continue;
                }
                refOccasion.setLastEmailDate(newLastEmailDate);
                   System.out.println("Returned value from sendim email attempt was: " + newLastEmailDate.getTime());
               }
               catch(Exception e){
                   System.out.println("There was an exception on sending reminding emails: " +e.getCause().getMessage());
               }
           }
           if(refOccasion.getEmailCanBeResent()){continue;}
           if(refOccasion.getLastEmailDate().compareTo(calendar) <= 0 ){
               refOccasion.setEmailCanBeResent(Boolean.TRUE);
               ror.save(refOccasion);
           }
            //if info has not been filled           
           
       }
       
   }
   
   public Calendar sendReferredOccasionEmail(Integer userId, String freq,Calendar lastSent){
       
       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.HOUR_OF_DAY, 0);
       calendar.set(Calendar.MINUTE, 0);
       calendar.set(Calendar.SECOND, 0);      
       calendar.set(Calendar.MILLISECOND, 0);
        Calendar testDate = null;        
        //Calendar compareDate2 = null;        
       System.out.println("Entered referred occasion email method");
       int i;
        //check daily reminder
        testDate = (Calendar) lastSent.clone();
        //compareDate2 = lastSent;
        testDate.add(Calendar.DAY_OF_YEAR,1);
        //compareDate2.add(Calendar.DAY_OF_YEAR,-1);
        //System.out.println("cal :" + lastSent.getTime());
        //System.out.println("compareDate +1 day:"+ testDate.getTime());
        //System.out.println("compareDate test :"+ compareDate2.getTime());
        i=testDate.compareTo(calendar);
        if(freq.equals("daily")) {
            if((i <= 0)){
                System.out.println("Entered daily");
                   //sends email daily
                   try  {
                      sendRemindingEmail(userId);
                      return calendar;
                    }
                   catch (IOException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
           }
        }
        //check weekly reminder
        testDate = null;
        testDate = (Calendar) lastSent.clone();
        testDate.add(Calendar.WEEK_OF_YEAR,1);
        //System.out.println("compareDate +1 week:"+ testDate.getTime());
        i=testDate.compareTo(calendar);
        if(freq.equals("weekly")) {
            if((i <= 0)){
                System.out.println("Entered weekly");
                try  {
                      sendRemindingEmail(userId);  
                      return calendar;
                    }
                   catch (IOException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
            }
        }
        
        
        //check monthly reminder
        testDate = null;
        testDate = (Calendar) lastSent.clone();
        testDate.add(Calendar.MONTH,1);
        //System.out.println("compareDate + 1 month:"+ testDate.getTime());
        i=testDate.compareTo(calendar);
        if(freq.equals("monthly")){
                if((i <= 0)){
                    System.out.println("Entered monthly");
                    try  {
                          sendRemindingEmail(userId);  
                          return calendar;
                        }
                    catch (IOException ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                }
        }
        
        //otherwise, the last sent mail returns to normal
        System.out.println("cal test again:" + lastSent.getTime());
        return lastSent;
   }

    public void checkForRecurringOccasions() {
        
       Iterable<ReferredOccasion> refOccasions = ror.findAll();
       
       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.HOUR_OF_DAY, 0);
       calendar.set(Calendar.MINUTE, 0);
       calendar.set(Calendar.SECOND, 0);      
       calendar.set(Calendar.MILLISECOND, 0);
       for(ReferredOccasion refOccasion : refOccasions){
           if(refOccasion.getRecurring()){
               Integer i = refOccasion.getOccasionDate().compareTo(calendar);
               if(i <= 0){
                   refOccasion.setRecurring(false);
                   ReferredOccasion referredOccasion = refOccasion;
                   Calendar newDate = refOccasion.getOccasionDate();
                   newDate.add(Calendar.YEAR, 1);
                   referredOccasion.setOccasionDate(newDate);
                   referredOccasion.setReferredOccasionId(null);
                   saveReferedOccasion(referredOccasion);
                   saveReferedOccasion(refOccasion);
               }
           }
       }
    }
   
}
