package com.jobsPortal.jobs_finite.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({ExcelFileException.class,GlobalException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleControllerExceptions(Exception ex){
        if(ex.getClass().equals(ExcelFileException.class)){
            return new ErrorMessage(new Date(),"Error occurred while reading excel file",ex.getMessage());
        }
        if(ex.getClass().equals(GlobalException.class)){
            return new ErrorMessage(new Date(),"Bad Request",ex.getMessage());
        }

        return new ErrorMessage(new Date(),"Required parameters cannot be empty or skipped",ex.getMessage());
    }
}
