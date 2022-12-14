package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "Video")
data class Video(
    @Id
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val userId: String? = null,
    val likes: Int = 0,
    val disLikes: Int = 0,
    val tags: Set<String> = setOf(),
    val videoUrl: String? = null,
    val videoStatus: VideoStatus? = null,
    val viewCount: Int = 0,
    val thumbnailUrl: String? = null,
    val comments: List<Comment> = listOf()
)