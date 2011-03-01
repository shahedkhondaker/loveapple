package cn.loveapple.service.util.service;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hibernate.validator.constraints.impl.EmailValidator;

import cn.loveapple.service.cool.model.FixedMailModel;

public class MailUtil {
	/**
	 * 
	 * @param fixedMail
	 * @param message
	 * @param toMail
	 * @param properties
	 * @return
	 * @throws MessagingException
	 */
	public static MimeMessage genMessageFromFixed(FixedMailModel fixedMail, MimeMessage message, String toMail, Map<String, Object> properties)
			throws MessagingException{
		if(StringUtils.isEmpty(toMail) || (!new EmailValidator().isValid(toMail, null))){
			return message;
		}
		if(fixedMail == null || message == null){
			return message;
		}

		StringWriter sw = new StringWriter();
		
		try {
			Velocity.init();
		
			VelocityContext context = new VelocityContext();
			if(properties != null){
				for (Entry<String, Object> entry : properties.entrySet()) {
					context.put(entry.getKey(), entry.getValue());
				}
			}
			Velocity.evaluate(context, sw, null, fixedMail.getBody());
	
			message.setFrom(fixedMail.getFromAddress());
			message.addRecipients(Message.RecipientType.TO, toMail);
			message.setSubject(fixedMail.getSubject(), fixedMail.getEncode());
			message.setText(sw.toString());
			
		} catch (Exception e) {
			throw new MessagingException("Canot init velocity", e);
		}finally{
			sw.flush();
		}

		
		return message;
	}
}
