package com.example.iismicroservice.service;

import com.example.iismicroservice.model.Message;
import com.example.iismicroservice.repository.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CozeService {

    private static final Logger logger = Logger.getLogger(CozeService.class.getName());

    @Value("${coze.api.url}")
    private String cozeApiUrl;

    @Value("${coze.api.token}")
    private String cozeApiToken;

    @Value("${coze.bot.id}")
    private String botId;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RestTemplate restTemplate;

    public String sendMessageToCoze(String username, String query) {
        String url = cozeApiUrl;

        // Создаем запрос
        CozeRequest request = new CozeRequest(botId, username, query);

        // Устанавливаем заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cozeApiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setConnection("keep-alive");
        headers.setAccept(List.of(MediaType.ALL));

        HttpEntity<CozeRequest> entity = new HttpEntity<>(request, headers);

        // Логируем запрос
        logger.info("Sending request to Coze: " + entity);

        // Отправляем запрос и получаем ответ
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Логируем полный ответ
        logger.info("Full response from Coze: " + response);

        // Логируем тело ответа
        logger.info("Response body from Coze: " + response.getBody());

        // Парсим тело ответа
        String responseBody = response.getBody();
        String cozeResponse = parseResponse(responseBody);

        // Сохраняем сообщение в базе данных
        Message message = new Message();
        message.setUsername(username);
        message.setQuery(query);
        message.setResponse(cozeResponse);
        message.setTimestamp(LocalDateTime.now()); // Устанавливаем текущее время
        messageRepository.save(message);

        return cozeResponse;
    }

    private String parseResponse(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode messagesNode = rootNode.path("messages");

            for (JsonNode messageNode : messagesNode) {
                String type = messageNode.path("type").asText();
                if ("answer".equals(type)) {
                    return messageNode.path("content").asText();
                }
            }

            return "No answer found";
        } catch (Exception e) {
            logger.severe("Error parsing response: " + e.getMessage());
            return "No response";
        }
    }
}