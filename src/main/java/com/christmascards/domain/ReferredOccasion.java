/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author HP PC
 */
@Entity
@Table(name="referred_occasion", schema="public")
public class ReferredOccasion implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="referred_occasion_id")
    private Integer referredOccasionId;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    
    @Column(name="first_name", length=36)
    private String friendFirstName;
    
    @Column(name="last_name", length=36)
    private String friendLastName;
    
    @Column(name="occasion", length=36)
    private String occasion;
    
    @Column(name="email", length=120)
    private String email;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="occasion_date")
    private Calendar occasionDate;
    
    @Column(name="relationship", length=36)
    private String relationship;
    
    @Column(name="address_line1", length=120)
    private String addressLine1;
    
    @Column(name="referrence_token", length=120, unique = true)
    private String referrenceToken;
    
    @Column(name="date_has_being_filled")
    private Boolean infoHasBeingFilled;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="last_email_date")
    private Calendar lastEmailDate;
    
    @Column(name="email_can_be_resent")
    private Boolean emailCanBeResent;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="referred_date")
    private Calendar referredDate;
    
    @Column(name="is_deleted")
    private Boolean isDeleted;
    
    @Column(name="address_line2")
    private String addressLine2;
    
    @Column(name="zip_code")
    private String zipCode;
    
    @Column(name="country")
    private String country;
    
    @Column(name="\"state\"")
    private String state;
    
    @Column(name="city")
    private String city;
    
    @Column(name="email_frequency")
    private String emailFrequency;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_edited_date")
    private java.util.Date lastEditedDate;
    
    @Column(name="last_edited_by")
    private String lastEditedBy;
    
    @Column(name="recurring")
    private Boolean recurring;
    
    @Transient
    private Calendar nextVerification;
    
    public ReferredOccasion() {
    }

    public Integer getReferredOccasionId() {
        return referredOccasionId;
    }

    public void setReferredOccasionId(Integer referredOccasionId) {
        this.referredOccasionId = referredOccasionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFriendFirstName() {
        return friendFirstName;
    }

    public void setFriendFirstName(String friendFirstName) {
        this.friendFirstName = friendFirstName;
    }

    public String getFriendLastName() {
        return friendLastName;
    }

    public void setFriendLastName(String friendLastName) {
        this.friendLastName = friendLastName;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getOccasionDate() {
        return occasionDate;
    }

    public void setOccasionDate(Calendar occasionDate) {
        this.occasionDate = occasionDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    public String getReferrenceToken() {
        return referrenceToken;
    }

    public void setReferrenceToken(String referrenceToken) {
        this.referrenceToken = referrenceToken;
    }

    public Boolean getInfoHasBeingFilled() {
        return infoHasBeingFilled;
    }

    public void setInfoHasBeingFilled(Boolean infoHasBeingFilled) {
        this.infoHasBeingFilled = infoHasBeingFilled;
    }


    public Calendar getLastEmailDate() {
        return lastEmailDate;
    }

    public void setLastEmailDate(Calendar lastEmailDate) {
        this.lastEmailDate = lastEmailDate;
    }

    public Boolean getEmailCanBeResent() {
        return emailCanBeResent;
    }

    public void setEmailCanBeResent(Boolean emailCanBeResent) {
        this.emailCanBeResent = emailCanBeResent;
    }

    public Calendar getReferredDate() {
        return referredDate;
    }

    public void setReferredDate(Calendar referredDate) {
        this.referredDate = referredDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public java.util.Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(java.util.Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public String getEmailFrequency() {
        return emailFrequency;
    }

    public void setEmailFrequency(String emailFrequency) {
        this.emailFrequency = emailFrequency;
    }

    public Calendar getNextVerification() {
        return nextVerification;
    }

    public void setNextVerification(Calendar nextVerification) {
        this.nextVerification = nextVerification;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

 
    
    
    
    
    enum Edited{
        USER,
        REFERRED
    }
    
}
