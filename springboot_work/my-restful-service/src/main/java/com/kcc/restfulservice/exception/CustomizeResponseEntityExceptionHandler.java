package com.kcc.restfulservice.exception;

import com.kcc.restfulservice.bean.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// 에러를 핸들링하기 위해 스프링부트에서 제공
// 모든 컨트롤러가 실행 될때 @ControllerAdvice 예외를 전담해서 처리
@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //모든 예외처리를 해주는 메서드
    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse
                = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exceptionResponse
                = new ExceptionResponse(new Date(), "Validation Fail", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    //UserNotFoundException 처리 해주는 메서드
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleUserNotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse
                = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
