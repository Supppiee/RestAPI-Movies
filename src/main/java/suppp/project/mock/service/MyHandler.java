package suppp.project.mock.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

//@ControllerAdvice - used to send any files as error responce
//@RestControllerAdvice - used to send JSON as error responce


@RestControllerAdvice 

public class MyHandler {
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> handle404(){
		Map<String, Object> map = new HashMap<>();
		map.put("error", "No Resource Found Exception");
		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handle400(){
		Map<String, Object> map = new HashMap<>();
		map.put("error", "Method Argument Type Mismatch Exception");
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handle405(){
		Map<String, Object> map = new HashMap<>();
		map.put("error", "Http Request Method Not Supported Exception");
		return new ResponseEntity<Object>(map, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
