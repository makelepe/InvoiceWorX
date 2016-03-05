package za.co.invoiceworx.mail;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import za.co.invoiceworx.util.JavaEmail;
import za.co.invoiceworx.util.MailObject;


/**
 * Message-Driven Bean implementation class for: QueueListenerMDB
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
        propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(
        propertyName = "destination", propertyValue = "queue/InvoiceWorXMailingQueue") })
public class QueueListenerMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public QueueListenerMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	
    	try {
    	
    	   if (message instanceof ObjectMessage) {
               System.out.println("Queue: I received an ObjectMessage at "
                               + new Date());
               ObjectMessage msg = (ObjectMessage) message;
               
               MailObject mailObject = (MailObject) msg.getObject();
               
           	//sending email
          	 
       	   JavaEmail javaEmail = new JavaEmail();
       	 
       	    javaEmail.setMailServerProperties();
       	    
			javaEmail.createEmailMessage(mailObject.getSubject(),mailObject.getToAddress(),mailObject.getMsg());
			
       	    javaEmail.sendEmail();
               
               

           } else {
               System.out.println("Not a valid message for this Queue MDB");
           }
    	   
    	} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

}
