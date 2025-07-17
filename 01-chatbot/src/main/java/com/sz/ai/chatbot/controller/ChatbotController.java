package com.sz.ai.chatbot.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 普通的聊天机器人
 */
@RestController
@RequestMapping("/chatbot")
public class ChatbotController {
    
    public static final DashScopeChatOptions DASH_SCOPE_CHAT_OPTIONS = DashScopeChatOptions.builder()
            .withModel("deepseek-r1")
            .withTopP(0.6)
            .withTemperature(0.5)
            .build();
    
    private static final String DEFAULT_PROMPT = """
            你是一个知识丰富的智能聊天助手,名字叫 Anora , 请您根据用户的提问回答.
            请您用中文回答.
            """;

    private final ChatClient chatClient;

    public ChatbotController(ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder
                .defaultSystem(DEFAULT_PROMPT)
                .defaultAdvisors(
                        SimpleLoggerAdvisor.builder().build()
                )
                .build();
    }

    @GetMapping("/simple")
    public String simple(@RequestParam(value = "query", defaultValue = "你好! 你的名字?") String query) {
        return chatClient
                .prompt(query)
                .options(ChatbotController.DASH_SCOPE_CHAT_OPTIONS)
                .call()
                .content();
    }
    
    @GetMapping("/stream")
    public Flux<String> stream(@RequestParam(value = "query", defaultValue = "你好! 你的名字?") String query
            , HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return chatClient
                .prompt(query)
                .options(DASH_SCOPE_CHAT_OPTIONS)
                .stream()
                .content();
    }

}
