package common.mailUtil;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.seleniumExceptionHandling.CustomExceptionHandler;
import common.xlUtil.DataTable;

public class MailUtil {

	private static String emailCredSheet="emailCred";
	
	public static boolean checkMailSubject(String mailSubject){
		boolean found=false;
		try {
			
			DataTable data=new DataTable(Constant.getTestDataFilePath(), emailCredSheet);
			
			String username=data.getValue( 1, "username"); 
			String password=data.getValue( 1, "password");
			//create properties field
			Properties properties = new Properties();

			properties.put("mail.pop3.host", "smtp.office365.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			//create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect("smtp.office365.com", username, password);

			//create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			//Checking the message content in top 5 messages only
			for (int i = messages.length-1; i>=0; i--) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());
				
				if(message.getSubject().contains(mailSubject)){
					found=true;
					break;
				}else if(i==(messages.length-5)){
					break;
				}
			}

			//close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			new CustomExceptionHandler(e);
		} catch (MessagingException e) {
			new CustomExceptionHandler(e);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		
		if(found){
			CustomReporter.report(STATUS.PASS, "Mail with subject: '"+mailSubject+" is received");
		}else{
			CustomReporter.report(STATUS.FAIL, mailSubject+" Mail recieved confirmation failed due to above mentioned issue");
		}
		
		return found;
	}
	
	public static void main(String[] args) {
		sendNotificationMail("TESTING AUTOMATION MAIL NOTIFICATION");
	}
	
	public static void sendNotificationMail(String desc){
		System.out.println("===============================================================================");
		System.out.println("Mail STARTED "+ new Date());
		if (!Constant.enableMailNotification) {
			System.out.println("Mail feature is STOPPED "+ new Date());
			System.out.println("===============================================================================");
			return;
		}
		DataTable data=new DataTable(Constant.getTestDataFilePath(), emailCredSheet);
		
		final String username=data.getValue(1, "username"); 
		final String password=data.getValue(1, "password");
		 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.getValue(1, "emailTo"))); 
			//TODO  "shailendra.rajawat@nextgenclearing.com";
			message.setSubject("Notification of Automated test execution : "+desc);
			

			
			//Attaching the html file
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(
					"<h3>"
					+  desc
					+ "</h3>"
					+ "<h3>Please find the attached HTML report for quick reference<br/><br/> -Shailendra </h3>",
					"text/html");
			 Multipart multipart = new MimeMultipart();
			 multipart.addBodyPart(messageBodyPart);
			 
			 messageBodyPart = new MimeBodyPart();
	         String filename = Constant.getResultHtmlFilePath();
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(Constant.reportRedesignTemplateName);
	         multipart.addBodyPart(messageBodyPart);
	         

	         BodyPart messageBodyPart1 = new MimeBodyPart();
	         String filename1 = Constant.getResultextenthtmlfilePath();
	         DataSource source1 = new FileDataSource(filename1);
	         messageBodyPart1.setDataHandler(new DataHandler(source1));
	         messageBodyPart1.setFileName(Constant.resultExtentHTMLFileName);
	         multipart.addBodyPart(messageBodyPart1);

	         // Send the complete message parts
	         message.setContent(multipart);
			 
			Transport.send(message);
			System.out.println("Mail Sent");
			

		} catch (MessagingException e) {
			if(e.toString().contains("AuthenticationFailed")){
				System.err.println("FAILED TO SEND MAIL : Authentication Failed for the username and password mentioned in Sheet ["+emailCredSheet+"] "+ Constant.getTestDataFilePath());
			}else{
				e.printStackTrace();	
			}
			
		}finally {
			System.out.println("Mail ENDED "+ new Date());
			System.out.println("===============================================================================");
		}
		
		
	}

}
