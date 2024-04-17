package dev.mehdizebhi.springdatameilisearchdemo.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

    protected String title;
    protected HttpStatus httpStatus;
    protected ErrorType type;

    public GenericException(String message, String title, HttpStatus httpStatus, ErrorType type) {
        super(message);
        this.title = title;
        this.httpStatus = httpStatus;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorType getType() {
        return type;
    }
}
