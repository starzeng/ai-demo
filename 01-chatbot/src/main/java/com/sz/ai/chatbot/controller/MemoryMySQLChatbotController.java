package com.sz.ai.chatbot.controller;

import com.alibaba.cloud.ai.memory.jdbc.MysqlChatMemoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @author StarryZeng
 * @version 1.0.0
 * @date 2025/7/18 00:44
 */
@RestController
@RequestMapping("/memory/mysql")
public class MemoryMySQLChatbotController {
    
    private static final int MAX_MESSAGE = 5;
    
    private final ChatClient chatClient;
    
    private final MessageWindowChatMemory messageWindowChatMemory;
    
    public MemoryMySQLChatbotController(ChatClient.Builder chatClientBuilder,
            MysqlChatMemoryRepository mysqlChatMemoryRepository) {
        
        this.messageWindowChatMemory = MessageWindowChatMemory.builder().chatMemoryRepository(mysqlChatMemoryRepository)
                .maxMessages(MAX_MESSAGE).build();
        
        this.chatClient = chatClientBuilder.defaultAdvisors(
                MessageChatMemoryAdvisor.builder(messageWindowChatMemory).build()).build();
    }
    
    @GetMapping("/call")
    public String call(@RequestParam(value = "query", defaultValue = "你好，你的名字?") String query,
            @RequestParam(value = "cid", defaultValue = "starryzeng") String cid) {
        return chatClient.prompt(query).advisors(
                a -> {
                    a.param(CONVERSATION_ID, cid);
                    a.advisors(SimpleLoggerAdvisor.builder().build());
                }
        ).call().content();
    }
    
    @GetMapping("/messages")
    public List<Message> messages(@RequestParam(value = "cid", defaultValue = "starryzeng") String cid) {
        return messageWindowChatMemory.get(cid);
    }
}
