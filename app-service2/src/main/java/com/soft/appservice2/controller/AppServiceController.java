package com.soft.appservice2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app2")
public class AppServiceController {

    @RequestMapping(value = "/getMessage",method = RequestMethod.GET)
    public String getMessage(){
        String reponse = String.format("current : appservice2-%s","getMessage");
        return reponse;
    }
}
