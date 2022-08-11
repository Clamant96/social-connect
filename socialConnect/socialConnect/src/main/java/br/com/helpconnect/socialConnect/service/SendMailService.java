package br.com.helpconnect.socialConnect.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import br.com.helpconnect.socialConnect.model.Usuario;

@Service
public class SendMailService {
	
	public Boolean sendMail(Usuario usuario) {

        // Recipient's email ID needs to be mentioned.
        String to = usuario.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "kevin.helpconnect@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {	

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("kevin.helpconnect@gmail.com", "<senha-email-gerada-gmail>");

            }

        });
        
        try {
        	
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress(from, "Help Connect"));

	      msg.setReplyTo(InternetAddress.parse(to, false));

	      msg.setSubject("Dados cadastrais", "UTF-8");

	      msg.setText("E-mail: "+ usuario.getEmail() +"\nUsername: "+ usuario.getUsername() +"\nLink: "+ usuario.getSenha(), "UTF-8");
	      
	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
	      
    	  Transport.send(msg);  

	      return true;
        }
	    catch (Exception e) {
	      e.printStackTrace();
	      
	      return false;
	    }

    }
	
}
