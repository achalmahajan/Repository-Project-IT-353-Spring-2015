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
import model.SignUpModel;

/**
 *
 * @author it3530123
 */
@Named(value = "javaMailApp")
@SessionScoped
public class SignUpMailApp implements Serializable {

    public static void mailapp(SignUpModel amail) {
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
            message.setSubject("MAIL APP SUCCESSFULLY RENDERED!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>Hi "+ amail.getFirstName()+ ", This is an email verification of your sign-up on Repository Project webpage.</h1>"
                    + "<img src=\"http://oi60.tinypic.com/156wc9w.jpg \" width=\"100\" height=\"100\">"
                    + "<br>" + "<b>First Name: " + amail.getFirstName() + "<br>"
                    + "<b>Last Name: </b>" + amail.getLastName() + "<br>" 
                    + "<b>Email id: </b>" + amail.getEmail() + "<br>" 
                    + "<b>Security Question is: </b>" + amail.getSecurityQuestion()+ "<br>" 
                    + "<b>Security Answer is: </b>" + amail.getSecurityAnswer() + "<br>"
                    + "<b>Reason for account is: </b>" + amail.getAccountReason()
                    ,"text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
