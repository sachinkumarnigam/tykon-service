/**
 * 
 */
package com.tykon.api.framework.service.core.notification.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tykon.api.framework.service.core.notification.api.INotifier;
import com.tykon.api.framework.service.core.notification.api.IRecipient;
import com.tykon.api.framework.service.core.notification.freemarker.processor.FreemarkerTemplateProcessor;
import com.tykon.api.framework.service.core.notification.model.NotificationEmailRequest;

/**
 * @author sachin
 *
 */
@Service
public class EmailNotifier implements INotifier{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FreemarkerTemplateProcessor processor;

	//@Autowired
	//private JavaMailSender javaMailSender;
	
	private JavaMailSender javaMailSender;
	 
    @Autowired
    public EmailNotifier(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

	@Value("${mail.default.from}")
	private String defaultFromEmailid;
	
	protected ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 
	 */
	public EmailNotifier() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Async
	public void sendMail(NotificationEmailRequest request) throws Exception {
		if(request.getToRecipients() == null || request.getToRecipients().size()==0) {
			throw new Exception("No recipient to send mail");
		}
		
		String msgBody="";
		if (request.getViewVars() != null && !request.getViewVars().isEmpty())
			msgBody = processor.processTemplate(request.getViewVars(), request.getTemplateFileName());

		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			if(request.getFrom() == null) {
				//helper.setFrom(defaultFromEmailid);
				helper.setFrom(defaultFromEmailid, "HELLO-PARENT");
			} else {
				helper.setFrom(request.getFrom().getRecipientAddress());
			}
			if(request.getBccRecipients() != null) {
				helper.setBcc(this.getInetAddress(request.getBccRecipients()));
			}
			if(request.getCcRecipients() != null) {
				helper.setCc(this.getInetAddress(request.getCcRecipients()));
			}
			helper.setTo(this.getInetAddress(request.getToRecipients()));
			helper.setSubject(request.getSubject());
			helper.setText(msgBody, true);

			// let's attach the infamous windows Sample file (this time copied to c:/)
			if(request.getAttachmentFileName() != null && request.getAttachmentStreamSource() != null) {
				//FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
				//helper.addAttachment("CoolImage.jpg", file);
				helper.addAttachment(request.getAttachmentFileName(), request.getAttachmentStreamSource());
			}
			javaMailSender.send(message);
		} catch(Exception e) {
			request.setViewVars(null);//making view var as null so that it wont create problem in below json serialization
			logger.error("exception in creating mail helper while sending with request "+objectMapper.writeValueAsString(request),e);
		}
		
	}
	private String[] getInetAddress(List<IRecipient> emailRecipients) {
		if(emailRecipients == null || emailRecipients.size()==0) {
			return null;
		}
		List<String> address = new ArrayList<>();
		emailRecipients.forEach((recipient) ->{try {
			address.add(recipient.getRecipientAddress());
		} catch (Exception e) {
			logger.error("exception in copying internet address "+recipient.getRecipientAddress()+ " ",e);
		}});
		String []addresses = new String[address.size()];
		return address.toArray(addresses);
	}
	
	
}
