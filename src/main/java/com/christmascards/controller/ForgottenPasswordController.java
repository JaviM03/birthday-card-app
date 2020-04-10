/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.controller;

import static com.christmascards.controller.MainController.REFERREDCODE;
import com.christmascards.domain.User;
import com.christmascards.repository.UserRepository;
import com.christmascards.service.PasswordService;
import com.christmascards.service.UserService;
import com.christmascards.util.PasswordUtils;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ForgottenPasswordController {
    
    @Autowired
    UserRepository userRepo;        
    
    @Autowired
    private PasswordService pass;
    
    //static final String server = ":8080/christmas-card-app";

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    Logger l = Logger.getLogger("logger");
    
    // Process form submission from forgotPassword page
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request) throws ParseException, IOException {

        ModelAndView mav = new ModelAndView();

        List<User> user = userRepo.findByEmail(userEmail);

        if (user == null || user.size()==0) {
            mav.addObject("response", "That email doesn't exist");
        } else {
            
            User u = user.get(0);

            // Generate random 36-character string token for reset password 
            u.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userRepo.save(u);
            //String appUrl = request.getScheme() + "://" + request.getServerName() + server;  
            // request.getScheme() + "://" + request.getServerName() + server;  
            // "http" + "://" + "localhost" + ":8080/christmas-card-app" 
            
            String appUrl = "https://christmas-card-app2.herokuapp.com";  
            //http://localhost:8080/christmas-card-app/reset?token=8b642767-8c0e-4704-b1d9-9b414dbba5d5            
            // "https" + "://" + "christmas-card-app2+.herokuapp.com" 
            //https://christmas-card-app2.herokuapp.com
            pass.sendResetPasswordEmail(u,appUrl);
            
            // Add success message to view
            mav.addObject("response", "Password recovery Email sent to " + userEmail);
        }

        mav.setViewName("password-reset");
        return mav;

    }
    
    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        Optional<User> user = userRepo.findUserByResetToken(token);

        if (user.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("response", "Invalid Password Link.");
        }

        modelAndView.setViewName("password-change");
        return modelAndView;
    }
    
    
    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(@RequestParam(value = "passwordNew") String pass1, @RequestParam(value = "passwordConfirm") String pass2, @RequestParam(value = "tokenId") String token) {

        ModelAndView modelAndView = new ModelAndView();
        
        if(pass1.equals(pass2)){
            // Find the user associated with the reset token
            Optional<User> user = userRepo.findUserByResetToken(token);

            // This should always be non-null but we check just in case
            if (user.isPresent()) {

                User resetUser = user.get();

                // Set new password    
                String salt = PasswordUtils.getSalt(30);
                String securePass = PasswordUtils.generateSecurePassword(pass1,salt);
                resetUser.setUserPassword(securePass);
                resetUser.setPassSalt(salt);

                // Set the reset token to null so it cannot be used again
                resetUser.setResetToken(null);

                // Save user
                userRepo.save(resetUser);

                // In order to set a model attribute on a redirect, we must use
                // RedirectAttributes
                //redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");
                modelAndView.setViewName("login");
                return modelAndView;

            } else {
                modelAndView.addObject("response", "Invalid Password link");
                modelAndView.setViewName("password-change");
            }
        }else{
            modelAndView.addObject("response", "Passwords must be the same");
            modelAndView.addObject("resetToken", token);
            modelAndView.setViewName("password-change");
        }

        return modelAndView;
    }
    
    
}
