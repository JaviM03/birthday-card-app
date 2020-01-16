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
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author HP PC
 */

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;
    
    @Transactional
    public User saveUser(User user){
        return userRepo.saveAndFlush(user);
    }
    
}
