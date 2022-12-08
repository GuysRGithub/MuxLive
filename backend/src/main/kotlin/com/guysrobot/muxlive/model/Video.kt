package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "Video")
data class Video(
    @Id
    private val id: String,
    private val title: String,
    private val description: String,
    private val userId: String,
    private val likes: Int,
    private val disLikes: Int,
    private val tags: Set<String>,
    private val videoUrl: String,
    private val videoStatus: VideoStatus,
    private val viewCount: Int,
    private val thumbnailUrl: String,
    private val comments: List<Comment>
)