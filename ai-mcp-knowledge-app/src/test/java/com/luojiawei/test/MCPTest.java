package com.luojiawei.test;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MCPTest {

    @Resource
    private ChatClient.Builder chatClientBuilder;

    @Autowired
    private ToolCallbackProvider tools;

    @Test
    public void test_tool() {
        String userInput = "有哪些工具可以使用";
        var chatClient = chatClientBuilder
                .defaultTools(tools)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("deepseek/deepseek-v3-turbo")
                        .build())
                .build();

        System.out.println("\n>>> QUESTION: " + userInput);
        System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
    }

    @Test
    public void test_workflow() {
        String userInput = "获取电脑配置";
        userInput = "在 C:/Users/luojiawei/Desktop 文件夹下，创建 电脑.txt";

        var chatClient = chatClientBuilder
                .defaultTools(tools)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("deepseek/deepseek-v3-turbo")
                        .build())
                .build();

        System.out.println("\n>>> QUESTION: " + userInput);
        System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
    }

}