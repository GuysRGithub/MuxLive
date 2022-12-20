package com.guysrobot.muxlive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.concurrent.ConcurrentHashMap

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
    private val _videoHistory: MutableSet<String> = ConcurrentHashMap.newKeySet(),
    private val _likedVideos: MutableSet<String> = ConcurrentHashMap.newKeySet(),
    private val _disLikedVideos: MutableSet<String> = ConcurrentHashMap.newKeySet(),
) {
    val likedVideos: Set<String> = _likedVideos
    val disLikedVideos: Set<String> = _disLikedVideos
    val videoHistory: Set<String> = _videoHistory

    fun addToLikedVideos(videoId: String) {
        _likedVideos.add(videoId)
    }

    fun addToDisLikedVideos(videoId: String) {
        _disLikedVideos.add(videoId)
    }

    fun removeFromLikedVideos(videoId: String) {
        _likedVideos.remove(videoId)
    }

    fun removeFromDisLikedVideos(videoId: String) {
        _disLikedVideos.remove(videoId)
    }

    fun addVideoHistory(videoId: String) {
        _videoHistory.add(videoId)
    }
}
