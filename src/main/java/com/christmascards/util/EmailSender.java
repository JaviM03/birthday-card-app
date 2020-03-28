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
    
    public static String EMAILFROM = "christmascards254@gmail.com";
    
    public static String sendEmail (String to, String mensaje, String subject, final String email, final String password) throws IOException{       
                Email fromEmail = new Email(email);
                Email toEmail = new Email(to);
                Content content = new Content("text/plain", mensaje);
                Mail mail = new Mail(fromEmail, subject, toEmail, content);
                SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    return "Email enviado";
                  } catch (IOException ex) {
                      System.out.println(ex.getMessage());
                    return "Error enviando mensaje";
                  }
                }
    
    public static String sendEmail(String to, String template,
            ArrayList<String> personalizationParameters, ArrayList<String> personalizationValues) throws IOException{       
                Email fromEmail = new Email(EMAILFROM);
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
                    return "Email enviado";
                  } catch (IOException ex) {
                      System.out.println(ex.getMessage());
                    return "Error enviando mensaje";
                  }
    
                }
    
    public static String sendHTMLEmail (String to, String mensaje, String subject) throws IOException{
        /*String respuesta;   
        Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email,password);
				}
			});
                
                	try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("User Created on Christmas App");
                        
                        
                        
			message.setContent(mensaje,"text/html; charset=utf-8");
			Transport.send(message);
                            System.out.println("Email Enviado");
			return "Email enviado";

		} catch (MessagingException e) {
                            System.out.println("Erro enviando email "+e.getMessage());
                        return "Error enviando mensaje";

		}*/
                Email fromEmail = new Email(EMAILFROM);
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
                    return "Email enviado";
                  } catch (IOException ex) {
                      System.out.println(ex.getMessage());
                    return "Error enviando mensaje";
                  }
                }
    
}
