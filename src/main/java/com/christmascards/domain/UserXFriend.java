/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.domain;

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
@Table(name="referred_friend", schema = "public")
public class UserXFriend {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="referred_friend_id")
    private Integer userXFriendId;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="household_name")
    private String householdName;
    
    @Column(name="address_line1")
    private String addressLine1;
    
    @Column(name="address_line2")
    private String addressLine2;
    
    @Column(name="city")
    private String city;
    
    @Column(name="state")
    private String state;
    
    @Column(name="postal_code")
    private String postalCode;
    
    @Column(name="country")
    private String country;
    
    @Column(name="email")
    private String email;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="birth_date")
    private Date birthDate;
    
    @Column(name="is_registered")
    private Boolean isRegistered;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Integer userId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="registered_user_ref")
    private Integer registeredUserRef;

    public UserXFriend() {
    }   
    
    public Integer getUserXFriendId() {
        return userXFriendId;
    }

    public void setUserXFriendId(Integer userXFriendId) {
        this.userXFriendId = userXFriendId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRegisteredUserRef() {
        return registeredUserRef;
    }

    public void setRegisteredUserRef(Integer registeredUserRef) {
        this.registeredUserRef = registeredUserRef;
    }
    
    
    
    
}
