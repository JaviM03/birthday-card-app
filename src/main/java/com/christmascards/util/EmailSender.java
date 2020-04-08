/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sendgrid.*;
import java.util.ArrayList;

/**
 *
 * @author HP PC
 */
public class EmailSender {
    
    
    public static String sendEmail(String to, String from, String template,
            ArrayList<String> personalizationParameters, ArrayList<String> personalizationValues) throws IOException{ 
                System.out.println("Send Email with Template selected");
                Email fromEmail = new Email(from);
                Email toEmail = new Email(to);
                Mail mail = new Mail();
                mail.setFrom(fromEmail);
                mail.setTemplateId(template);
                Personalization personalization = new Personalization();
                personalization.addTo(toEmail);
                for(int i = 0; i<personalizationParameters.size();i++){
                    personalization.addDynamicTemplateData(personalizationParameters.get(i), personalizationValues.get(i));
                }
                mail.addPersonalization(personalization);
                SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println("Mensaje al usuario enviado. " + response.getStatusCode());
                    System.out.println("Body del return: " + response.getBody());
                    return response.getStatusCode()+"";
                  } catch (IOException ex) {
                      System.out.println(ex.getMessage());
                    return "Error enviando mensaje";
                  }
    
                }
    
    public static String sendHTMLEmail (String to, String from, String mensaje, String subject) throws IOException{
                Email fromEmail = new Email(from);
                Email toEmail = new Email(to);
                Content content = new Content("text/html", mensaje);
                Mail mail = new Mail(fromEmail, subject, toEmail, content);
                SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println("Email statusCode: " + response.getStatusCode());
                    System.out.println("Email responseBody: " + response.getBody());
                    System.out.println("Email responseHeaders: " + response.getHeaders());
                    return response.getStatusCode()+"";
                  } catch (IOException ex) {
                      System.out.println(ex.getMessage());
                    return "Error enviando mensaje";
                  }
                }
    
}
