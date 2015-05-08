package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Evangelos on 4/4/2015.
 */
@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    public MailService() {
    }

    public Boolean sendMail(String from, String to, String subject, String messagebody) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "150.140.142.39");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail", "mail");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(messagebody,"text/html; charset=UTF-8");
            Transport.send(message);
            LOGGER.debug("Mail Sent To:" + to + " : " + messagebody);
            return true;
        } catch (MessagingException e) {
            LOGGER.debug("Mail Failed To:" + to + " : " + messagebody + " " + e.getMessage());

        }
        return false;
    }

    public Boolean passwordReminder(String to, String password){
        String message="Όνομα Χρήστη:"+to+"<br> Νέος Κωδικός Πρόσβασης: "+ password;
        message+="<br>Αλλάξτε τον κωδικό σας πρόσβασης στο Προφιλ σας μόλις συνθεθείτε στο Σύστημα του AndroidCampaigns";
        return sendMail("theodori@ceid.upatras.gr",to,"Sepe Password Reminder",message);
    }

    public Boolean createNewUser(String email, String username, String password, String firstName, String lastName){
        String message="Παρακάτω θα βρείτε τα στοιχεία για την είσοδό σας στο Σύστημα του AndroidCampaigns";
        message+="<br>Username: " + username + "<br>Email Address: "+ email + "<br>Κωδικός Πρόσβασης: "+ password;
        message+="<br>Όνομα χρήστη: " + firstName + "<br>Επώνυμο χρήστη: " + lastName;
        return sendMail("theodori@ceid.upatras.gr",email,"Sepe New Account",message);
    }
}
