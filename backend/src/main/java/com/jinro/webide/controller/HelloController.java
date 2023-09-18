package com.jinro.webide.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public ResponseEntity<?> hello(){
        return new ResponseEntity<>("hello", HttpStatusCode.valueOf(Response.SC_OK));
    }
}