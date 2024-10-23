package com.akash.Aks.Service;

import java.util.Random;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.List;
import java.util.Properties;

import com.akash.Aks.DataObjects.FileDataDao;
import com.akash.Aks.DataObjects.UserDao;
import com.akash.Aks.Factory.RepoFactory;
import com.akash.Aks.Repository.IUserRepo;

public class UserServiceImpe implements IUserService {

	@Override
	public String generateOtp() {
		
		Random random=new Random();
		
		  return String.format("%04d", random.nextInt(10000));
			
	}

	@Override
	public void sendOto(String email, String getotp) {
		

		
		 
		        // Recipient's email ID needs to be mentioned.
		        String to = email;

		        // Sender's email ID needs to be mentioned
		        String from = "sharmaakash96145@gmail.com";

		        // Assuming you are sending email from through gmails smtp
		        String host = "smtp.gmail.com";

		        // Get system properties
		        Properties properties = System.getProperties();

		        // Setup mail server
		        properties.put("mail.smtp.host", host);
		        properties.put("mail.smtp.port", "465");
		        properties.put("mail.smtp.ssl.enable", "true");
		        properties.put("mail.smtp.auth", "true");

		        // Get the Session object.// and pass username and password
		        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

		            protected PasswordAuthentication getPasswordAuthentication() {

		                return new PasswordAuthentication(from, "qbwy frzr uexs uaoo");

		            }

		        });

		        // Used to debug SMTP issues
		        session.setDebug(true);

		        try {
		            // Create a default MimeMessage object.
		            MimeMessage message = new MimeMessage(session);

		            // Set From: header field of the header.
		            message.setFrom(new InternetAddress(from));

		            // Set To: header field of the header.
		            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		            // Set Subject: header field
		            message.setSubject("File Enc ka OTP");

		            // Now set the actual message
		            message.setText("Your One time Password for File Enc app is " + getotp);

		            System.out.println("sending...");
		            // Send message
		            Transport.send(message);
		            System.out.println("Sent message successfully....");
		        } catch (MessagingException mex) {
		            mex.printStackTrace();
		        }

		    }

		


	

	@Override
	public int saveUser(UserDao user) {
		IUserRepo repo=RepoFactory.getRepoFactory();
		return repo.saveUser(user);
	}

	@Override
	public boolean isExists(String email) {
	 IUserRepo repo=RepoFactory.getRepoFactory();
	 return repo.isExists(email);
	}

	@Override
	public List<FileDataDao> getAllFiells() {
		IUserRepo repo=RepoFactory.getRepoFactory();
		return repo.getAllFiells();
	}

	@Override
	public int deleteFile(int id) {
		IUserRepo repo=RepoFactory.getRepoFactory();
		return repo.deleteFile(id);
	}

	@Override
	public int saveFile(FileDataDao data) {
		IUserRepo repo=RepoFactory.getRepoFactory();
		return repo.saveFile(data);
	}

}
