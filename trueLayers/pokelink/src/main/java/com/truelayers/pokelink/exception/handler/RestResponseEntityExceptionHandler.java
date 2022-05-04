package com.truelayers.pokelink.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.truelayers.pokelink.exception.CustomInternalException;

import feign.FeignException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String GENERIC_INTERNAL_SERVER_ERROR = "Generic Server Internal Error";
    private Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    
    @Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
    	logger.error("handleTypeMismatchException exception :",ex);
        ErrorMessage error = new ErrorMessage();
       
    	String errorString =((TypeMismatchException) ex).getPropertyName()+":"+((TypeMismatchException) ex).getMessage();
             
    	errorString= !StringUtils.isEmpty(errorString) ? errorString :"invalid arguments";
    	HttpStatus code =HttpStatus.BAD_REQUEST;
    	error.setErrorMessage(errorString);
    	error.setStatusCode(code);
        return new ResponseEntity(error,code);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		   
	    	logger.error("handleConversionNotSupported exception :",ex);
	        ErrorMessage error = new ErrorMessage();
	       
	    	String errorString =((ConversionNotSupportedException) ex).getPropertyName()+":"+((ConversionNotSupportedException) ex).getCause();
	             
	    	errorString= !StringUtils.isEmpty(errorString) ? errorString :"invalid arguments";
	    	HttpStatus code =HttpStatus.BAD_REQUEST;
	    	error.setErrorMessage(errorString);
	    	error.setStatusCode(code);
	    	return new ResponseEntity(error,code);
	}

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
    	 
    	logger.error("handleMethodArgumentNotValid exception :",ex);
        ErrorMessage error = new ErrorMessage();
       
    	List<String> errors =((MethodArgumentNotValidException) ex).getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField()+ ":"+ x.getDefaultMessage())
                .collect(Collectors.toList());
    	String errorString= errors!=null && !errors.isEmpty() ? errors.stream().collect(Collectors.joining(";")) :"invalid arguments";
    	HttpStatus code =HttpStatus.BAD_REQUEST;
    	error.setErrorMessage(errorString);
    	error.setStatusCode(code);
    	return new ResponseEntity(error,code);
	}

	@ExceptionHandler(value
            = {IllegalArgumentException.class, Exception.class, RuntimeException.class,  CustomInternalException.class,
            ConstraintViolationException.class})
    protected ResponseEntity<?> handleConflict(Exception ex) {
    	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                 .currentRequestAttributes())
                 .getRequest();
     	logger.error("handleConflict exception :",ex);

        HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage error = new ErrorMessage();
        error.setErrorMessage(GENERIC_INTERNAL_SERVER_ERROR);
        if (ex instanceof CustomInternalException) {
            error.setErrorMessage(((CustomInternalException) ex).getCustomErrorMessage());
            code = ((CustomInternalException) ex).getHttpStatusCode();
        }
       
        if (ex instanceof FeignException) {
            error.setErrorMessage(((FeignException) ex).getMessage());
            code = HttpStatus.resolve( ((FeignException) ex).status());
        }
        if(ex instanceof ConstraintViolationException) {
        	List<String> errors =((ConstraintViolationException)ex).getConstraintViolations().stream()
        			.map(x -> x.getConstraintDescriptor().toString()).collect(Collectors.toList());
        	String errorString= errors!=null && !errors.isEmpty() ? errors.stream().collect(Collectors.joining(";")) :"invalid arguments";
        	error.setErrorMessage(errorString);
        	 code =HttpStatus.BAD_REQUEST;
        }
        error.setStatusCode(code);
        
        return new ResponseEntity<>(error, code);
    }
}
