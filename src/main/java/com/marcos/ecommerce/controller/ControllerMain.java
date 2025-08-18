package com.marcos.ecommerce;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("Run")
public class ControllerMain {

    @GetMapping
    public final String helloRun(){
        return "{\"Status\":\"API rodando normalmente\"}";
    } 
}
