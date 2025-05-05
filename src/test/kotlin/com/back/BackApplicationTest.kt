package com.back

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackApplicationTest {

    @Autowired
    lateinit var chatClient: OpenAiChatModel

    @Test
    @DisplayName("test chat model")
    fun t1() {
        println(chatClient.call("너를 소개해줘"))
    }

}
