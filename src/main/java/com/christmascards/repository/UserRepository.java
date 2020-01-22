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
    public User findOne(Integer id);

    @Override
    public <S extends User> S findOne(Example<S> exmpl);
    
    public List<User> findByEmail(String email);
    
    public List<User> findByPhoneNumber(String phoneNumber);
      
    public User findFirstByEmail(String email);
}
