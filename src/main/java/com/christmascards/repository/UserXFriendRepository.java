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
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author HP PC
 */

@Repository
public interface UserXFriendRepository extends PagingAndSortingRepository<UserXFriend, Integer>{
    
    @Override
    public List<UserXFriend> findAll();

    @Override
    public UserXFriend findOne(Integer id);
    
    @Override
    public Page<UserXFriend> findAll(Pageable pgbl);
    
    public Page<UserXFriend> findByUser(User user, Pageable pageable);
    
    public List<UserXFriend> findByFriend(Friend friend);
    
    public Page<UserXFriend> findByUserAndIsDeletedOrderByReferredDateDesc(User user, Boolean isDeteled, Pageable pageable);
    
    public Page<UserXFriend> findByUserAndIsDeletedAndReferredDateBetweenOrderByReferredDateDescUserXFriendIdDesc(User user, Boolean isDeleted, Calendar referredDateStart, Calendar referredDateEnd, Pageable pageable);
}
