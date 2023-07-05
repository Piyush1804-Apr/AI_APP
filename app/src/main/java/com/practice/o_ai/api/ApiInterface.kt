package com.practice.o_ai.api

import com.practice.o_ai.models.chat.ChatModel
import com.practice.o_ai.models.imageresponse.GenerateImageModel
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.lang.reflect.Type

interface ApiInterface {

     @POST("/api/completions")
     suspend fun getChat(
         @Header("Content-Type") contentType: String,
         @Header("Authorization") authorization :String,
         @Body requestBody: RequestBody

     ) :ChatModel


    @POST("/api/images/generations")
    suspend fun generateImage() : GenerateImageModel

}