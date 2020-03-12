/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.service;

import com.christmascards.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.christmascards.domain.*;
import org.springframework.stereotype.Service;
/**
 *
 * @author HP PC
 */
@Service
public class FriendService {
    
    @Autowired
    FriendRepository friendRepo;
    
    
    public Friend saveFriend(Friend friend){
        return friendRepo.saveAndFlush(friend);
    }
    
}
