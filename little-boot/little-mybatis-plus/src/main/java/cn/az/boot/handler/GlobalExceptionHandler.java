package cn.az.boot.handler;

import cn.az.boot.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author az
 * @since 11/15/20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceExceptionHandler(ServiceException se) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(se.getMessage());
    }
}
