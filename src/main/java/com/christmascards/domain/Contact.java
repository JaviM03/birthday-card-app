/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.domain;

import java.util.Calendar;
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
@Table(name="contact", schema = "public")
public class Contact {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="contact_id")
    private Integer contactId;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="last_email_date")
    private Calendar lastEmailDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="referred_date")
    private Calendar referredDate;
    
    @Column(name="relationship")
    private String relationship;
    
    @Column(name="address_line1")
    private String addressLine1;
    
    @Column(name="address_line2")
    private String addressLine2;
    
    @Column(name="country")
    private String country;
    
    @Column(name="state")
    private String state;
    
    @Column(name="last_edited_by")
    private String lastEditedBy;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="last_edited_date")
    private Calendar lastEditedDate;
    
    @Column(name="referrence_token", unique = true)
    private String referrence_token;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Contact() {
    }

    
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getLastEmailDate() {
        return lastEmailDate;
    }

    public void setLastEmailDate(Calendar lastEmailDate) {
        this.lastEmailDate = lastEmailDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public Calendar getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Calendar lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public String getReferrence_token() {
        return referrence_token;
    }

    public void setReferrence_token(String referrence_token) {
        this.referrence_token = referrence_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
