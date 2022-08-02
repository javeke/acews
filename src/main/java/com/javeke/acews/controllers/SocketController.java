package com.javeke.acews.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    public static final Logger logger = LoggerFactory.getLogger(SocketController.class);


    @Autowired
    public SocketController(){}

    @MessageMapping("/test")
    @SendTo("/")
    public String testConnection(String value){
        logger.info("Testing connection: {}", value);
        return "Success";
    }
}
