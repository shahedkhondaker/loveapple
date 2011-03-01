package cn.loveapple.service.cool.service.exception;

import javax.mail.MessagingException;

/**
 * メール送受信時に発生した例外
 * 
 * @author hao_shunri
 * @since 2011/02/28
 * @version $Revision$
 */
public class MailException extends MessagingException {

	public MailException() {
		super();
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(String message, Exception cause) {
		super(message, cause);
	}

}
