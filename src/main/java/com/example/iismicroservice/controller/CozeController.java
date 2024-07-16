package com.example.iismicroservice.controller;

import com.example.iismicroservice.service.CozeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coze")
public class CozeController {

    @Autowired
    private CozeService cozeService;

    @PostMapping("/message")
    public String sendMessage(@RequestParam String user, @RequestParam String query) {
        return cozeService.sendMessageToCoze(user, query);
    }
}
