/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
/**
 *
 * @author HP PC
 */
public class TwilioVerification implements VerificationService {

    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String VERIFICATION_SID = System.getenv("VERIFICATION_SID");

    public TwilioVerification() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public VerificationResult startVerification(String phone, String channel) {
        try {
            Verification verification = Verification.creator(VERIFICATION_SID, phone, channel).create();
            return new VerificationResult(verification.getSid());
        } catch (ApiException exception) {
            return new VerificationResult(new String[] {exception.getMessage()});
        }
    }

    @Override
    public VerificationResult checkVerification(String phone, String code) {
        try {
            VerificationCheck verification = VerificationCheck.creator(VERIFICATION_SID, code).setTo(phone).create();
            if("approved".equals(verification.getStatus())) {
                return new VerificationResult(verification.getSid());
            }
            return new VerificationResult(new String[]{"Invalid code."});
        } catch (ApiException exception) {
            return new VerificationResult(new String[]{exception.getMessage()});
        }
    }
}
