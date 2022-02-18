package etg.example.demo.utils;

import org.springframework.http.HttpStatus;

public class ErrorExceptionHandler extends Throwable {

    private HttpStatus httpStatus;
    private String message;

    public ErrorExceptionHandler() {
    }

    public ErrorExceptionHandler(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
