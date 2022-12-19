package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "User")
data class User(
    @Id
    private val id: String? = null,
    private val firstName: String? = null,
    private val lastName: String? = null,
    private val fullName: String? = null,
    private val email: String? = null,
    private val sub: String? = null,
    private val subscribedToUsers: Set<String> = setOf(),
    private val subscriber: Set<String> = setOf(),
    private val videoHistory: Set<String> = setOf(),
    private val likedVideos: Set<String> = setOf(),
    private val disLikedVideos: Set<String> = setOf(),
)
