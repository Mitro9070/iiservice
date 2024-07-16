package com.example.iismicroservice.websocket;

import com.example.iismicroservice.service.CozeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private CozeService cozeService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userMessage = message.getPayload();
        String response = cozeService.sendMessageToCoze("example_user", userMessage);
        session.sendMessage(new TextMessage(response));
    }
}