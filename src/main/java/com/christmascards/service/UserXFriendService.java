/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.service;

import org.springframework.stereotype.Service;
import com.christmascards.domain.*;
import com.christmascards.repository.UserRepository;
import com.christmascards.repository.UserXFriendRepository;
import com.christmascards.util.EmailSender;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 *
 * @author HP PC
 */

@Service
public class UserXFriendService {
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    UserXFriendRepository userXFriendRepository;
    
    @Autowired
    UserRepository userRepo;
    
    public Page<UserXFriend> getUsersFriends(User user, String dateRange, Integer page){
        Calendar dateStart = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.clear(Calendar.MINUTE);
        dateStart.clear(Calendar.SECOND);
        dateStart.clear(Calendar.MILLISECOND);
        if(dateRange == null){
            return userXFriendRepository.findByUserAndIsDeletedOrderByReferredDateDesc(user, Boolean.FALSE, new PageRequest(page, 10));
        }
        if(dateRange.equals("monthly")){
            dateStart.set(Calendar.DAY_OF_MONTH, 1);
        }
        else if(dateRange.equals("weekly")){
            dateStart.set(Calendar.DAY_OF_WEEK, dateEnd.getFirstDayOfWeek());
        }
        else{
            return userXFriendRepository.findByUserAndIsDeletedOrderByReferredDateDesc(user, Boolean.FALSE, new PageRequest(page, 10));
        }
        return userXFriendRepository.findByUserAndIsDeletedAndReferredDateBetweenOrderByReferredDateDescUserXFriendIdDesc(user, Boolean.FALSE, dateStart, dateEnd, new PageRequest(page, 10));
    }
    
    
    public List<UserXFriend> getAllFriends(){
        return userXFriendRepository.findAll();
    }
    
    public Page<UserXFriend> getUsersFriends( User user, Integer page){
        User searchUser = new User();
        searchUser.setUserId(user.getUserId());
        return userXFriendRepository.findByUserAndIsDeletedOrderByReferredDateDesc(searchUser, Boolean.FALSE, new PageRequest(page, 10));
    }
    
    public UserXFriend addNewUserFriend(UserXFriend friend, String occasionDate) throws ParseException, IOException{
        Calendar currentDate = Calendar.getInstance();
        Date date = format.parse(occasionDate);
        Calendar cal = Calendar.getInstance();
        Calendar referredDate = Calendar.getInstance();
        cal.setTime(date);
        friend.setIsDeleted(Boolean.FALSE);
        friend.setFriendIsRegistered(Boolean.FALSE);
        friend.setOccasionDate(cal);
        friend.setReferredDate(referredDate);
        UserXFriend savedFriend = userXFriendRepository.save(friend);
        sendFriendEmail(savedFriend.getFriend());
        return savedFriend;
    }
    
    public void sendFriendEmail(Friend friend) throws IOException{
        System.out.println("SendEmail----");
        String message = "Hey " + friend.getFirstName()+"! One of your friends just requested for you to join our site and update your info.";
        EmailSender.sendEmail(friend.getEmail(), message, "Reminding you to update your info!", "christmascards254@gmail.com", System.getenv("EMAIL_PASSWORD"));
        List<UserXFriend> friendsCatal = userXFriendRepository.findByFriend(friend);
        Calendar date = Calendar.getInstance();
        for(int i=0; i<friendsCatal.size();i++){
            friendsCatal.get(i).setLastEmailDate(date);
            userXFriendRepository.save(friendsCatal.get(i));
        }
    }
    
    public void sendFriendRemindingEmail(Integer userXFriendId) throws IOException{
        
        UserXFriend usXFriend = userXFriendRepository.findOne(userXFriendId);
        String message = "Hey " + usXFriend.getFriend().getFirstName()+"! One of your friends wanted to remind you to join our site and update your info.";
        EmailSender.sendEmail(usXFriend.getFriend().getEmail(), message, "Reminding you to update your info!", "christmascards254@gmail.com", System.getenv("EMAIL_PASSWORD"));
        UserXFriend friend = userXFriendRepository.findOne(userXFriendId);
        Calendar calendar = Calendar.getInstance();
        friend.setLastEmailDate(calendar);
        userXFriendRepository.save(friend);
    }
}
