package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "User")
data class User(
    @Id
    private val id: String,
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private val subscribedToUsers: Set<String>,
    private val subscriber: Set<String>,
    private val videoHistory: Set<String>,
    private val likedVideos: Set<String>,
    private val disLikedVideos: Set<String>,
)
