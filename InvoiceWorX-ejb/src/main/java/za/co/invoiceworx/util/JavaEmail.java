package za.co.invoiceworx.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import za.co.invoiceworx.ejb.SecurityEJB;
public class JavaEmail {
	
	
	Properties emailProperties;
	  Session mailSession;
	  MimeMessage emailMessage;
	  private final Logger log = Logger.getLogger(JavaEmail.class);
	
	 
	  public void setMailServerProperties() {
	 
	    String emailPort = "587";//gmail's smtp port 465 587
	 
	    emailProperties = System.getProperties();
	    emailProperties.put("mail.smtp.port", emailPort);
	    //emailProperties.put("mail.smtp.socketFactory.fallback", "true");
	    emailProperties.put("mail.smtp.auth", "true");
	    emailProperties.put("mail.smtp.starttls.enable", "true");
	 
	  }
	 
	  public void createEmailMessage(String subject,String toEmail,String msg) throws AddressException,
	      MessagingException {
	    //String[] toEmails = { "ngoatvm@gmail.com" };
		String toAddress =  toEmail ;
	    String emailSubject = subject;

	 
	    mailSession = Session.getDefaultInstance(emailProperties, null);
	    emailMessage = new MimeMessage(mailSession);
	 
	  //  for (int i = 0; i < toEmails.length; i++) {
	      emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
	    //}
	 
	    emailMessage.setSubject(emailSubject);
	    emailMessage.setContent(msg, "text/html");//for a html email
	    //emailMessage.setText(emailBody);// for a text email
	 
	  }
	 
	  public void sendEmail() throws AddressException, MessagingException {
	 
	    String emailHost = "smtp.gmail.com";
	    String fromUser = "vtpms123";//just the id alone without @gmail.com
	    String fromUserEmailPassword = "vt32541150?";
	 
	    Transport transport = mailSession.getTransport("smtp");
	 
	    transport.connect(emailHost, fromUser, fromUserEmailPassword);
	    transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
	    transport.close();
	    log.info("Email sent successfully.");
	  }

}
