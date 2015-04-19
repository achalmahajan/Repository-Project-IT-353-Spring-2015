/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamailapp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.AdminApprovalBean;

/**
 *
 * @author it3530123
 */
@Named(value = "adminRejectMailApp")
@SessionScoped
public class AdminRejectMailApp implements Serializable {

    /**
     * Creates a new instance of AdminRejectMailApp
     */
    public AdminRejectMailApp() {
    }
    
    public static void mailapp(AdminApprovalBean amail) {
        
        String str = amail.getEmail();
        // Recipient's email ID needs to be mentioned.
        String to = amail.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "achaina@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "yourID"); // if needed
        properties.setProperty("mail.password", "yourPassword"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Regarding Repository Account credentials!");
            
             message.setContent("Hi " + amail.getFirstName()+ ", </br>This is an email is to let you know that unfortunately your request for account is not been accpeted.</h1> <br/>"
                    + "<b>Reason for rejection is: </b>" + amail.getRejectReason()
                    ,"text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    
}
