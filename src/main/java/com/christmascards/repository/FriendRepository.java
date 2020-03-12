/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.christmascards.domain.*;
import org.springframework.data.domain.Example;
/**
 *
 * @author HP PC
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{

    @Override
    public <S extends Friend> S findOne(Example<S> exmpl);

    @Override
    public <S extends Friend> S saveAndFlush(S s);
    
    
}
