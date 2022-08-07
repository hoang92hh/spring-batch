package com.example.spring_batch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping ("/h")
    public ModelAndView goHome(){
        System.out.println("vao trang home");
        ModelAndView mav = new ModelAndView("/home");
        return mav;
    }
}
