package spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 将这个异常映射为HTTP状态码
 * @author only_TG
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="money not found.")
public class MoneyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 145L;
	
	
}
