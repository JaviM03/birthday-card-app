/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

import com.christmascards.service.ReferredOccasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author HP PC
 */
@Configuration
@EnableScheduling
public class EmailAvailabilityHelper {
    
    @Autowired
    ReferredOccasionService refOcService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 10000)
    public void runEmailCheck(){
        System.out.println("-----------Run email check triggered.---------------");
        //Checks to be used monthly, weekly or daily
        refOcService.checkIfReferedOccasionEmailCanBeSent();
    }

    public EmailAvailabilityHelper() {
    }
    
    
    
}
