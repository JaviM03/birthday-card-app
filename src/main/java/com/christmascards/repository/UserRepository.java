/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.christmascards.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
/**
 *
 * @author HP PC
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Override
    public List<User> findAll();

    @Override
    public Optional<User> findById(Integer id);
    

    
    public List<User> findByEmail(String email);
    
    public List<User> findByPhoneNumber(String phoneNumber);
      
    public User findFirstByEmail(String email);
    
    public Optional<User> findUserByResetToken(String resetToken);

}
