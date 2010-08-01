package cn.loveapple.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundエラー
 * 
 * @author hao_shunri
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6022356531046011354L;
	
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
	
}
