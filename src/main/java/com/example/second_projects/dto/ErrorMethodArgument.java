package com.example.second_projects.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorMethodArgument {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodArgumentError(MethodArgumentNotValidException e){
        List<ErrorDto> list = e.getFieldErrors().stream().map(fieldError -> {
            String field = fieldError.getField();
            String s = String.valueOf(fieldError.getRejectedValue());
            String defaultMessage = fieldError.getDefaultMessage();
            return new ErrorDto(field, String.format("Message :: %s, Rejection :: %s", defaultMessage, s));
        }).toList();

        return ResponseEntity.badRequest().body(ResponseDto.<Void>builder()
                        .code(-4)
                        .massage("Validation Error!")
                        .errors(list)
                .build());
    }

}
