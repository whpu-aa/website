package whpuaa.website.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import whpuaa.website.controller.model.HttpCommonErrorResponse;

@ControllerAdvice("whpuaa.website.controller")
public class CommonControllerAdvice {
    @ExceptionHandler
    HttpCommonErrorResponse handleInvalidModelException(InvalidModelException e) {
        return new HttpCommonErrorResponse(100000, e.getMessage());
    }
}
