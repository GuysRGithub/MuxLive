package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id

data class Model(
    @Id
    private val id: String,
    private val text: String,
    private val authorId: String,
    private val likes: Int,
    private val disLikes: Int
)
