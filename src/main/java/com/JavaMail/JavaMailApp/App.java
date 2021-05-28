package com.JavaMail.JavaMailApp;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Java Mail API
 *
 */
public class App {
	public static void main(String[] args) {
		
		
		System.out.println("-------------------------");
		System.out.println("--------Mail Send Application--------");
		System.out.println("-------------------------");
		String message = "Account Created Successfully!!!!";
		String subject = "Hello World!!";
		String to = "user1@gmail.com";
		String from = "user2@gmail.com";

		// <1> Mail without any images
		App.sendMail(message, subject, to, from);

		// <2> Mail with images
		App.sendFileWithMail(message, subject, to, from);

	}
	
	//Logic for sending the mail without any image
	
	public static void sendMail(String message, String subject, String to, String from) {

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.host", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("user2@gmail.com", "user");
			}
		});

		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(from);
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);

			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Logic for sending the mail with image
	
	public static void sendFileWithMail(String message, String subject, String to, String from) {

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.host", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("user2@gmail.com", "user");
			}
		});

		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(from);
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);

			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			MimeBodyPart textMimeBody = new MimeBodyPart();

			textMimeBody.setText(message);
			String path = "c://users/Downloads";
			File file = new File(path);
			try {
				mimeBodyPart.attachFile(file);
				mimeMultipart.addBodyPart(mimeBodyPart);
				mimeMultipart.addBodyPart(textMimeBody);
			} catch (Exception e) {
				e.printStackTrace();
			}

			mimeMessage.setContent(mimeMultipart);
			Transport.send(mimeMessage);
			System.out.println("Mail is send with the file attached to it.....");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
