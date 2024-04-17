package dev.mehdizebhi.springdatameilisearchdemo.exception;

import dev.mehdizebhi.springdatameilisearchdemo.dto.ResponseModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseModel<Void>> generalHandler(GenericException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(
                        ResponseModel.builder()
                                .error(exception, request.getRequestURI())
                                .build()
                );
    }
}
