package com.guysrobot.muxlive.dto

import com.guysrobot.muxlive.model.Comment

data class CommentDto(
    private val text: String,
    private val authorId: String,
) {
    fun toComment(): Comment {
        return Comment(text = text, authorId = authorId)
    }
}
