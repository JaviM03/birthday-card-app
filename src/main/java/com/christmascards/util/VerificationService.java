/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

/**
 *
 * @author HP PC
 */
public interface VerificationService {

    VerificationResult startVerification(String phoneNumber, String channel);

    VerificationResult checkVerification(String phoneNumber, String code);
}
