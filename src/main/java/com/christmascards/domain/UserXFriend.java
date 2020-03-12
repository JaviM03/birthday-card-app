/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author HP PC
 */
@Entity
@Table(name="user_friend_catalog", schema = "public")
public class UserXFriend implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_friend_catalog_id")
    private Integer userXFiendId;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="referred_friend_id")
    private Friend friend;
    
    @Column(name="friend_is_registered")
    private Boolean friendIsRegistered;
    
    @Column(name="relationship")
    private String relationship;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="last_email_date")
    private Calendar lastEmailDate;
    
    @Column(name="is_deleted")
    private Boolean isDeleted;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="referred_date")
    private Calendar referredDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="occasion_date")
    private Calendar occasionDate;
    
    @Column(name="occasion")
    private String occasion;

    public UserXFriend() {
    }

    public Integer getUserXFiendId() {
        return userXFiendId;
    }

    public void setUserXFiendId(Integer userXFiendId) {
        this.userXFiendId = userXFiendId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public Boolean getFriendIsRegistered() {
        return friendIsRegistered;
    }

    public void setFriendIsRegistered(Boolean friendIsRegistered) {
        this.friendIsRegistered = friendIsRegistered;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Calendar getLastEmailDate() {
        return lastEmailDate;
    }

    public void setLastEmailDate(Calendar lastEmailDate) {
        this.lastEmailDate = lastEmailDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Calendar getReferredDate() {
        return referredDate;
    }

    public void setReferredDate(Calendar referredDate) {
        this.referredDate = referredDate;
    }

    public Calendar getOccasionDate() {
        return occasionDate;
    }

    public void setOccasionDate(Calendar occasionDate) {
        this.occasionDate = occasionDate;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }
    
    
    
}
