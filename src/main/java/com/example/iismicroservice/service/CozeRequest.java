package com.example.iismicroservice.service;

import java.util.List;
import java.util.Map;

public class CozeRequest {
    private String bot_id;
    private String user;
    private String query;
    private String conversation_id;
    private List<Object> chat_history;
    private boolean stream;
    private Map<String, Object> custom_variables;

    public CozeRequest(String bot_id, String user, String query) {
        this.bot_id = bot_id;
        this.user = user;
        this.query = query;
        this.stream = false; // Default to non-streaming response
    }

    // Getters and setters
    public String getBot_id() {
        return bot_id;
    }

    public void setBot_id(String bot_id) {
        this.bot_id = bot_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    public List<Object> getChat_history() {
        return chat_history;
    }

    public void setChat_history(List<Object> chat_history) {
        this.chat_history = chat_history;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public Map<String, Object> getCustom_variables() {
        return custom_variables;
    }

    public void setCustom_variables(Map<String, Object> custom_variables) {
        this.custom_variables = custom_variables;
    }
}
