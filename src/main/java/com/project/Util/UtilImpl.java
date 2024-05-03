package com.project.Util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.project.Model.BooleanAndMessage;

public class UtilImpl implements Util {
	public Connection get() {
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Social_Media", "", "");
		}
		catch(SQLException e) {
			System.out.println("Driver Issue!");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Connection Issue!");
		}
		return con;
	}
	
	public BooleanAndMessage mailSender(final String from,final String to, final String subject, final String msg) {
		Properties props = System.getProperties();
		final String password = "";
		
	    props.setProperty("mail.smtp.host", "smtp.gmail.com");
	    props.setProperty("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
             }});
		try {
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(msg, "text/html; charset=utf-8");
			
			Transport.send(message);
			
			System.out.println("Mail Sent Successfully");
			return new BooleanAndMessage("OTP sent to registered E-mail account!", true);
		}
		catch(MessagingException e) { 
			e.printStackTrace(); 
			return new BooleanAndMessage("Unable to send E-mail! Try again later!", false);
		}
		
	}
	
	public String hashPassword(String password) {
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
            return null;
        }

        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
	}
	
	public String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public String generateOTP() {
		Random rand = new Random();
		int number = rand.nextInt(999999);
		return String.format("%6d", number);
	}
	
}
