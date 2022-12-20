package com.guysrobot.muxlive.model

import com.guysrobot.muxlive.dto.CommentDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(value = "Comment")
data class Comment(
    @Id
    private val id: String = UUID.randomUUID().toString(),
    private val text: String,
    private val authorId: String,
    private val likes: Int = 0,
    private val disLikes: Int = 0
) {
    fun toDTO(): CommentDto {
        return CommentDto(text = text, authorId = authorId)
    }
}
