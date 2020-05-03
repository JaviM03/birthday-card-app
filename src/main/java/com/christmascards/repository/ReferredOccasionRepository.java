/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.repository;

import com.christmascards.domain.*;
import java.util.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP PC
 */
@Repository
public interface ReferredOccasionRepository extends PagingAndSortingRepository<ReferredOccasion, Integer>{

    @Override
    public Iterable<ReferredOccasion> findAll();

    @Override
    public ReferredOccasion findOne(Integer id);

    @Override
    public <S extends ReferredOccasion> Iterable<S> save(Iterable<S> itrbl);

    @Override
    public <S extends ReferredOccasion> S save(S s);

    public ReferredOccasion findFirstByReferrenceToken(String referrenceToken);
   

    //Find All with page, necesary parameters are added
    public Page<ReferredOccasion> findByIsDeletedOrderByReferredDateDescReferredOccasionIdDesc(Boolean isDeleted, Pageable pageable);
    
    //Find by User and not deleted friends
    public Page<ReferredOccasion> findByUserAndIsDeletedOrderByReferredDateDescReferredOccasionIdDesc(User user, Boolean isDeleted, Pageable Page);
    
    //Find by User, Not deleted friends referrences and by Date
    public Page<ReferredOccasion> findByUserAndIsDeletedAndReferredDateBetweenOrderByReferredDateDescReferredOccasionIdDesc(User user, Boolean isDeleted, Calendar referredDateStart,
            Calendar referredDateEnd, Pageable Page);
      
    public Page<ReferredOccasion> findByUserAndIsDeletedOrderByReferredOccasionIdDesc(User user, Boolean isDeleted, Pageable pageable);
    
    @Query(value="select * from referred_occasion where\n" +
            "is_deleted = false and user_id = :userId and\n" +
            "(occasion ilike :searchWord or first_name ilike :searchWord \n" +
            "or last_name ilike :searchWord or email ilike :searchWord \n" +
            "or country ilike :searchWord or \"state\" ilike :searchWord \n" +
            " or city ilike :searchWord) ORDER BY ?#{#pageable}",
            nativeQuery = true,
            countQuery = "select count(*) from referred_occasion where\n" +
            "is_deleted = false and user_id = :userId and\n" +
            "(occasion ilike :searchWord or first_name ilike :searchWord \n" +
            "or last_name ilike :searchWord or email ilike :searchWord \n" +
            "or country ilike :searchWord or \"state\" ilike :searchWord \n" +
            " or city ilike :searchWord) ORDER BY ?#{#pageable}")
    Page<ReferredOccasion> findReferredOccasionByUserAndSearchWord(@Param("userId") Integer userId, @Param("searchWord") String searchWord, Pageable page);
}
