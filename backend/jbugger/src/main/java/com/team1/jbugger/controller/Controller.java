package com.team1.jbugger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

}