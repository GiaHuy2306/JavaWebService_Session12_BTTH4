package com.btth4.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Bắt lỗi StudentNotFoundException
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex) {
        // YÊU CẦU LOGGING: WARN khi StudentNotFoundException bị ném
        logger.warn("[SLF4J WARN] Xử lý ngoại lệ: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Bắt mọi lỗi hệ thống không mong muốn khác (Lỗi Chia cho 0, NullPointer...)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        // YÊU CẦU LOGGING: ERROR khi có lỗi không mong muốn
        logger.error("[SLF4J ERROR] Lỗi hệ thống nghiêm trọng hệ thống: ", ex);

        ErrorResponse error = new ErrorResponse("Đã xảy ra lỗi hệ thống không mong muốn!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
