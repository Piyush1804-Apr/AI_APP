package com.practice.o_ai.models

import com.practice.o_ai.models.imageresponse.GenerateImageModel

data class MessageModel(

    var isUser :Boolean,
    var isImage : Boolean,
    var message :String

)
