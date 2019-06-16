package com.example.controller;

import com.example.mq.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private SendMessage service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = {"/send", "/send/{name}"}, method = RequestMethod.GET)
    @ResponseBody
    public String send(@PathVariable(value = "name", required = false) String name) {
        return service.sendToRPC(name);
    }
}
