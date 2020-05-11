

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
    
    
    
    @RequestMapping(value="/forgot-password")
    public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        mav.addObject("response", null);
        mav.addObject("failedLogin", null);
        mav.setViewName("password-reset");
        return mav;
        
    }
    
    // Process form submission from forgotPassword page
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request) throws ParseException, IOException {

        ModelAndView mav = new ModelAndView();

        List<User> user = userRepo.findByEmail(userEmail);

        if (user == null || user.size()==0) {
            mav.addObject("response", null);
            mav.addObject("failedLogin", true);
        } else {
            
            User u = user.get(0);

            // Generate random 36-character string token for reset password 
            u.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userRepo.save(u);
            //String appUrl ="http" + "://" + "localhost" + ":8080/christmas-card-app" ;
            //String appUrl = request.getScheme() + "://" + request.getServerName() + server;  
            // request.getScheme() + "://" + request.getServerName() + server;  
            // "http" + "://" + "localhost" + ":8080/christmas-card-app" ;
            
            String appUrl = "http://app.mydigitaladdressbook.com";  
            //http://localhost:8080/christmas-card-app/reset?token=8b642767-8c0e-4704-b1d9-9b414dbba5d5            
            // "https" + "://" + "christmas-card-app2+.herokuapp.com" 
            //https://christmas-card-app2.herokuapp.com
            pass.sendResetPasswordEmail(u,appUrl);
            
            // Add success message to view
            //mav.addObject("response", "Password recovery Email sent to " + userEmail);
            mav.addObject("response", true);
            mav.addObject("failedLogin", null);
        }

        mav.setViewName("password-reset");
        return mav;

    }
    
    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam(name="token",required=false) String token, HttpServletResponse response,
            HttpServletRequest request) throws IOException {

        Optional<User> user = userRepo.findUserByResetToken(token);
        
        modelAndView.addObject("failedPass", null);
        
        if (user.isPresent() && token!=null) { // Token found in DB
            modelAndView.addObject("resetToken", token);
            modelAndView.setViewName("password-change");
        } else { // Token not found in DB
            response.sendRedirect(request.getContextPath()+"/login?invalidLink=true");
            return null;
        }

        return modelAndView;
    }
    
    
    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(@RequestParam(value = "passwordNew") String pass1, @RequestParam(value = "passwordConfirm") String pass2,
            @RequestParam(value = "tokenId") String token, HttpServletResponse response, HttpServletRequest request) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        
        // Find the user associated with the reset token
        Optional<User> user = userRepo.findUserByResetToken(token);
        
        if(user.isPresent() && token!=null){
            
            // This should always be non-null but we check just in case
            if (pass1.equals(pass2)) {

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
                response.sendRedirect(request.getContextPath()+"/login?successfulReset=true");
                return null;

            } else {                
                modelAndView.addObject("failedPass", true);
                modelAndView.addObject("resetToken", token);
                modelAndView.setViewName("password-change");
            }
        }else{
            response.sendRedirect(request.getContextPath()+"/login?invalidLink=true");
            return null;
        }

        return modelAndView;
    }
    
    
}
