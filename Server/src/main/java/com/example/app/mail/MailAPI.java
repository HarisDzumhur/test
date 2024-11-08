package com.example.app.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.app.client.RegisteredClientService;
import com.example.app.exception.MailException;

public class MailAPI {

	private static String USERNAME,PASSWORD;
	
	static {
		try(InputStream input=MailAPI.class.getClassLoader().getResourceAsStream("mail.properties")){
		ResourceBundle bundle=new PropertyResourceBundle(input);
		USERNAME = bundle.getString("username");
		PASSWORD = bundle.getString("password");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	private static Properties loadMailConfig() throws FileNotFoundException, IOException {
		Properties mailProp = new Properties();
		mailProp.load(MailAPI.class.getClassLoader().getResourceAsStream("mail.properties"));
			
		return mailProp;
	}
	
	
	public static void sendMail(String to,String title,String body)
	{
		Properties props =null;
		try {
		props = loadMailConfig();
		}catch(Exception ex)
		{
			throw new MailException("Error sending mail, properties failed!");
		}
		
		
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(USERNAME, PASSWORD);
	        }
	    });
	    
	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(USERNAME));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        message.setSubject(title);
	        message.setText(body);
	        
	        Transport.send(message);
	        
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	throw new MailException("Error sending mail, transport fail!");
	    }
	    
		
	}
	
	
	
	
}
