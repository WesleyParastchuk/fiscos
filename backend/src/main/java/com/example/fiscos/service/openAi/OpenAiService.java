package com.example.fiscos.service.openAi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final ChatClient chatClient;

    public OpenAiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String sendPrompt(String prompt) {
        return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
    }
    
}


