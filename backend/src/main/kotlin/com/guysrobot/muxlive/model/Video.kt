package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "Video")
data class Video(
    @Id
    private val id: String? = null,
    private val title: String? = null,
    private val description: String? = null,
    private val userId: String? = null,
    private val likes: Int = 0,
    private val disLikes: Int = 0,
    private val tags: Set<String> = setOf(),
    private val videoUrl: String? = null,
    private val videoStatus: VideoStatus? = null,
    private val viewCount: Int = 0,
    private val thumbnailUrl: String? = null,
    private val comments: List<Comment> = listOf()
)