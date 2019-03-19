package spittr.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpittrExceptionHandler {

	
	@ExceptionHandler(HandsomeNotFoundException.class)
	public String HandsomeNotFoundExceptionHandler() {
		
		
		return "error/handsome";
	}
}
