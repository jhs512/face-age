package com.back

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.content.Media
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.MimeTypeUtils
import java.net.URI

@SpringBootTest
class BackApplicationTest {

    @Autowired
    lateinit var chatClient: OpenAiChatModel

    @Test
    @DisplayName("test chat model")
    fun t1() {
        println(chatClient.call("너를 소개해줘"))
    }

    @Test
    @DisplayName("이주빈 배우의 나이 추정")
    fun t2() {
        // 1. 이미지 URL 정의
        val imageUrl = "https://cdn.jsdelivr.net/gh/jhs512/face-age/src/main/resources/static/1989_female_actor1_img_1.jpg"

        // 2. URI로 래핑하여 Media 객체 생성
        val media = Media(
            MimeTypeUtils.IMAGE_JPEG,        // JPEG 타입 지정
            URI.create(imageUrl)             // 문자열을 URI로 변환
        )

        // 3. UserMessage를 builder 패턴으로 생성
        val userMessage = UserMessage.builder()
            .text("이 이미지에서 보이는 사람의 나이를 추정해줘, 그렇게 생각한 이유를 최대한 자세히 설명해줘")  // 텍스트 프롬프트 설정
            .media(media)                                    // 멀티모달 입력(이미지) 추가
            .build()                                         // 최종 메시지 객체 생성

        // 4. Llama4 Scout 모델 옵션 지정
        val options = OpenAiChatOptions.builder()
            .model("meta-llama/llama-4-scout-17b-16e-instruct")
            .build()

        // 5. Prompt에 UserMessage와 옵션 담아 호출
        val prompt = Prompt(userMessage, options)
        val response = chatClient.call(prompt)

        // 6. 결과 출력
        println(response.result.output.text)
    }

    @Test
    @DisplayName("현봉식 배우의 나이 추정")
    fun t3() {
        // 1. 이미지 URL 정의
        val imageUrl = "https://cdn.jsdelivr.net/gh/jhs512/face-age/src/main/resources/static/1984_male_actor2_img_1.png"

        // 2. URI로 래핑하여 Media 객체 생성
        val media = Media(
            MimeTypeUtils.IMAGE_JPEG,        // JPEG 타입 지정
            URI.create(imageUrl)             // 문자열을 URI로 변환
        )

        // 3. UserMessage를 builder 패턴으로 생성
        val userMessage = UserMessage.builder()
            .text("이 이미지에서 보이는 사람의 나이를 추정해줘, 그렇게 생각한 이유를 최대한 자세히 설명해줘")  // 텍스트 프롬프트 설정
            .media(media)                                    // 멀티모달 입력(이미지) 추가
            .build()                                         // 최종 메시지 객체 생성

        // 4. Llama4 Scout 모델 옵션 지정
        val options = OpenAiChatOptions.builder()
            .model("meta-llama/llama-4-scout-17b-16e-instruct")
            .build()

        // 5. Prompt에 UserMessage와 옵션 담아 호출
        val prompt = Prompt(userMessage, options)
        val response = chatClient.call(prompt)

        // 6. 결과 출력
        println(response.result.output.text)
    }
}
