/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

import com.christmascards.service.ReferredOccasionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HP PC
 */
public class EmailAvailabilityHelper {
    
    @Autowired
    ReferredOccasionService refOcService;
    
    public void runEmailCheck(){
        //Checks to be used monthly, weekly or daily
        refOcService.checkIfReferedOccasionEmailCanBeSent();
    }

    public EmailAvailabilityHelper() {
    }
    
    
    
}
