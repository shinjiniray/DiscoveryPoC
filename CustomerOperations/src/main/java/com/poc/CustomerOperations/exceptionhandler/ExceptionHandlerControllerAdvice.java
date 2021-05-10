package com.poc.CustomerOperations.exceptionhandler;

import com.poc.CustomerOperations.exceptionhandler.ExceptionResponse;
import com.poc.CustomerOperations.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {


    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ResponseEntity<Object> handleException(final ServiceException exception,
                                           final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        ErrorDetails errorDetails = new ErrorDetails();
        error.setErrorMessage(exception.getMessage());
        error.setCode(exception.getCode());
        error.setType(exception.getType());
        List<ExceptionResponse> exceptionResponseList = new ArrayList<>();
        exceptionResponseList.add(error);
        errorDetails.setExceptionResponse(exceptionResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(errorDetails);
    }

}
