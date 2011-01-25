package cn.loveapple.service.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundエラーを返す場合、該当する例外を投げる。
 * 
 * @author hao_shunri
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 * <code>serialVersionUID</code> のコメント
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * エラーコード
	 */
	private Long id;
	
	/**
	 * @param id
	 */
	public ResourceNotFoundException(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public Long getName() {
		return id;
	}

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
