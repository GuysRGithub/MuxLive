package com.guysrobot.muxlive.controller

import com.guysrobot.muxlive.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("register")
    fun register(authentication: Authentication): String {
        val jwt = authentication.principal as? Jwt ?: return "Failed to registration"
        userService.register(jwt.tokenValue)
        return "User registration successfully"
    }

    @PostMapping("/subscribe/{userId}")
    fun subscribe(@PathVariable("userId") userId: String): Boolean {
        userService.subscribe(userId)
        return true
    }

    @PostMapping("/unsubscribe/{userId}")
    fun unsubscribe(@PathVariable("userId") userId: String): Boolean {
        userService.unsubscribe(userId)
        return true
    }

    @GetMapping("/{userId}/history")
    fun history(@PathVariable("userId") userId: String) : Set<String> {
        return userService.getVideosHistory(userId)
    }

}