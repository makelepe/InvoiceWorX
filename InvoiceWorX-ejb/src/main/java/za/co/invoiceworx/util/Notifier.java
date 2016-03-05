package za.co.invoiceworx.util;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author F4657314
 */
public class Notifier {
    
    public Boolean sendSms (String message, String destination) {
        return true;
    }
    
    public Boolean sendEmail (String subject, String body, String reciepient) {
    	
    	final String QUEUE_LOOKUP = "queue/InvoiceWorXMailingQueue";
        final String CONNECTION_FACTORY = "ConnectionFactory";
 

        try{
            Context context = new InitialContext();
            QueueConnectionFactory factory =
                (QueueConnectionFactory)context.lookup(CONNECTION_FACTORY);
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session =
                connection.createQueueSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);
 
            Queue queue = (Queue)context.lookup(QUEUE_LOOKUP);
            QueueSender sender = session.createSender(queue);

 
            // Sending ObjectMessage to the Queue
            ObjectMessage objMsg = session.createObjectMessage();
 
            
            //set up message
            MailObject mail = new MailObject();
            mail.setSubject(subject);
            mail.setToAddress(reciepient);
            mail.setMsg(body);
            objMsg.setObject(mail);
            
            sender.send(objMsg);//send msg to queue
            
            System.out.println(" Sent ObjectMessage to the Queue");
 
            session.close();
            
            return true;
           // connection.close();
        }catch(Exception e){e.printStackTrace();
        }
        
        return false;
        
    }
    
}
