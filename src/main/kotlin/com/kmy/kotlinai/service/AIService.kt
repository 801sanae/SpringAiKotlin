package com.kmy.kotlinai.service

import com.kmy.kotlinai.dataClass.Request
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.image.ImagePrompt
import org.springframework.ai.model.Media
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.OpenAiImageModel
import org.springframework.ai.openai.OpenAiImageOptions
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import java.net.URL
import java.util.Map;

@Service
class AIService(
    private var openAiChatModel: OpenAiChatModel,
    private var openAiImageModel: OpenAiImageModel
) {
    fun chatbot(request: Request): String {

        var promptTemplate = PromptTemplate(
            """ 
            You are a helpful assistant.\n
            나의 질문은 : {message}
            """
        )

//        print(">>>"+promptTemplate.template)

//        val prompt = promptTemplate.create(Map.of<String, Any>("message", requesSTTt.text))
        val prompt = promptTemplate.create(mapOf(Pair("message", request.text)))

        println(">>>> 전체 : " + prompt.toString())
//        println(">>>> model : " + prompt.options.model)
//        println(">>>> temperature :" + prompt.options.temperature);
        println(">>>> contents :" + prompt.contents)

        return openAiChatModel.call(prompt).result.output.content
    }

    fun imageAi(request: Request) : String {
        val userMessage = UserMessage(
            "이사진이 보이나요?",
            listOf(
                Media(
                    MimeTypeUtils.IMAGE_JPEG,
                    URL(request.text)
                )
            )
        )

        return openAiChatModel.call(userMessage)
    }

    fun imageAiGen(request: Request) : String{

        return openAiImageModel.call(
            ImagePrompt(request.text, OpenAiImageOptions.builder()
                                                    .withQuality("hd")
                                                    .withN(1)
                                                    .withHeight(1024)
                                                    .withWidth(1024)
                                                    .build()
            )
        ).result.output.url
    }
}