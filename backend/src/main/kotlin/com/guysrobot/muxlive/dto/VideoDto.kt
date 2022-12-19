package com.guysrobot.muxlive.dto

import com.guysrobot.muxlive.model.VideoStatus

data class VideoDto(
    val id: String?,
    val title: String? = null,
    val description: String? = null,
    val tags: Set<String> = setOf(),
    val videoUrl: String? = null,
    val videoStatus: VideoStatus? = null,
    val thumbnailUrl: String? = null,
    val likeCount: Int = 0,
    val disLikeCount: Int = 0,
)