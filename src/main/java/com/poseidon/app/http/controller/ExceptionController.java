package com.poseidon.app.http.controller;

import com.poseidon.app.service.CrudService.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ModelAndView handleApiException(EntityNotFoundException ex, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "Entity not found.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("404");
        return mav;
    }
}
