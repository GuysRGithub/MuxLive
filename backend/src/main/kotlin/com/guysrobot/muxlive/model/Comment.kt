package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "Comment")
data class Comment(
    @Id
    private val id: String,
    private val text: String,
    private val authorId: String,
    private val likes: Int,
    private val disLikes: Int
)
