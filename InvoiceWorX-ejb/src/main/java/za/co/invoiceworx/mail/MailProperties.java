package za.co.invoiceworx.mail;

import java.io.Serializable;

public class MailProperties implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject, email;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
