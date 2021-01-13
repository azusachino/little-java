package cn.az.boot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author Liz
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Throwable e, HttpServletResponse res) {
        log.info(e.getMessage() + res.getStatus());
        return ResponseEntity.of(Optional.of(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
