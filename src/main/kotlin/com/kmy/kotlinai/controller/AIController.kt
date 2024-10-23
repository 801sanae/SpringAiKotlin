package com.kmy.kotlinai.controller

import com.kmy.kotlinai.dataClass.Request
import com.kmy.kotlinai.service.AIService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ai")
class AIController(
    private val aiService : AIService
) {

    @PostMapping
    fun test(
        @RequestBody request : Request
    ){
        println(aiService.chatbot(request))
    }
    @PostMapping("/img")
    fun img(
        @RequestBody request : Request
    ){
        println(aiService.imageAi(request))
    }

    @PostMapping("/gen")
    fun gen(
        @RequestBody request : Request
    ){
        println(aiService.imageAiGen(request))
    }
}