package com.team.ghana.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MethodArgumentNotValidEx {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomError methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processErrors(fieldErrors);
    }

    private CustomError processErrors(List<FieldError> fieldErrors) {
        CustomError error;
        String description = "";
        for (FieldError fieldError: fieldErrors) {
            description += "-> " + fieldError.getField() + ": " + fieldError.getDefaultMessage() + "\n";
        }
        error = new CustomError(HttpStatus.BAD_REQUEST.value(),"Validation Error", description);
        return error;
    }

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach( (error) -> { String fieldName = ((FieldError) error).getField();
                                       String errorMessage = error.getDefaultMessage();
                                       errors.put(fieldName, errorMessage); }
                );
        return errors;
    }
    */

}
