package com.guysrobot.muxlive.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.guysrobot.muxlive.dto.UserInfoDto
import com.guysrobot.muxlive.model.User
import com.guysrobot.muxlive.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class UserService(
    private val repository: UserRepository,
    @Value("auth0.userinfo-endpoint")
    private val userInfoEndpoint: String
) {

    fun register(jwtToken: String) {
        // Make call to user info endpoint
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(userInfoEndpoint))
            .setHeader("Authorization", "Bearer $jwtToken")
            .build()

        val client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build()

        try {
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            val userInfoDto = ObjectMapper().apply {
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            }.readValue(response.body(), UserInfoDto::class.java)

            val user = User(
                firstName = userInfoDto.givenName,
                lastName = userInfoDto.familyName,
                fullName = userInfoDto.name,
                email = userInfoDto.email,
                sub = userInfoDto.sub,
            )

            repository.save(user)

        } catch (e: Exception) {
            throw RuntimeException("Error occurred when registration user", e)
        }

        // Fetch user info and save to database
    }

    val currentUser: User
        get() {
            val sub = (SecurityContextHolder.getContext().authentication.principal as Jwt).getClaim<String>("sub")

            return repository.findBySub(sub)
                .orElseThrow { IllegalArgumentException("Failed to find user with sub $sub") }
        }

    fun addToLikedVideos(videoId: String) {
        currentUser.run {
            addToLikedVideos(videoId)
            repository.save(this)
        }
    }

    fun removeFromLikedVideos(videoId: String) {
        currentUser.run {
            removeFromLikedVideos(videoId)
            repository.save(this)
        }
    }

    fun addToDisLikedVideos(videoId: String) {
        currentUser.run {
            addToDisLikedVideos(videoId)
            repository.save(this)
        }
    }
    fun removeFromDisLikedVideos(videoId: String) {
        currentUser.run {
            removeFromLikedVideos(videoId)
            repository.save(this)
        }
    }

    fun ifLikedVideo(videoId: String): Boolean {
        return currentUser.likedVideos.contains(videoId)
    }

    fun ifDisLikedVideo(videoId: String): Boolean {
        return currentUser.disLikedVideos.contains(videoId)
    }
}