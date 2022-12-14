package com.guysrobot.muxlive.model

import com.guysrobot.muxlive.dto.VideoDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

@Document(value = "Video")
data class Video(
    @Id
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val userId: String? = null,
    val likes: AtomicInteger = AtomicInteger(0),
    val disLikes: AtomicInteger = AtomicInteger(0),
    val tags: Set<String> = setOf(),
    val videoUrl: String? = null,
    val videoStatus: VideoStatus? = null,
    val viewCount: AtomicInteger = AtomicInteger(0),
    val thumbnailUrl: String? = null,
    private val _comments: MutableList<Comment> = CopyOnWriteArrayList()
) {
    val comments: List<Comment> = _comments
    fun like() {
        likes.incrementAndGet()
    }

    fun unLike() {
        likes.decrementAndGet()
    }

    fun dislike() {
        disLikes.incrementAndGet()
    }

    fun unDislike() {
        disLikes.decrementAndGet()
    }

    fun toDTO(): VideoDto {
        return VideoDto(
            id = id,
            title = title,
            description = description,
            tags = tags,
            videoUrl = videoUrl,
            videoStatus = videoStatus,
            thumbnailUrl = thumbnailUrl,
            likeCount = likes.get(),
            disLikeCount = disLikes.get()
        )
    }

    fun view() {
        viewCount.incrementAndGet()
    }

    fun addComment(comment: Comment) {
        _comments.add(comment)
    }
}