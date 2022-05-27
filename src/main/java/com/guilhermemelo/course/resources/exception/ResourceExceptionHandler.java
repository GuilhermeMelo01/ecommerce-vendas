package com.guilhermemelo.course.resources.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.guilhermemelo.course.services.exception.AuthorizationException;
import com.guilhermemelo.course.services.exception.DataIntegrityException;
import com.guilhermemelo.course.services.exception.FileException;
import com.guilhermemelo.course.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(HttpStatus.NOT_FOUND.value(), onfe.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stde);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException die, HttpServletRequest request) {

        StandardError stde = new StandardError(HttpStatus.BAD_REQUEST.value(), die.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException manve,
                                                                         HttpServletRequest request){
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validator Error!", System.currentTimeMillis());
        for (FieldError fieldError : manve.getBindingResult().getFieldErrors()){
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(HttpStatus.FORBIDDEN.value(), onfe.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(stde);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(HttpStatus.BAD_REQUEST.value(), onfe.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException onfe, HttpServletRequest request) {

        HttpStatus code = HttpStatus.valueOf(onfe.getErrorCode());
        StandardError stde = new StandardError(code.value(), onfe.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(code).body(stde);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(HttpStatus.BAD_REQUEST.value(), onfe.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(HttpStatus.BAD_REQUEST.value(), onfe.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }
}
