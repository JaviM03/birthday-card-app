/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

import com.christmascards.domain.User;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP PC
 */
public class LoginVerification {
    
    public static boolean sessionCheck(HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("loggedUser")!=null){
            if(request.getSession().getAttribute("loggedUser").getClass() == user.getClass()){
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }
}
