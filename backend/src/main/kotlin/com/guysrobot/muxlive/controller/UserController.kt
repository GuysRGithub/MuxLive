package com.guysrobot.muxlive.controller

import com.guysrobot.muxlive.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("register")
    fun register(authentication: Authentication): String {
        val jwt = authentication.principal as? Jwt ?: return "Failed to registration"
        userService.register(jwt.tokenValue)
        return "User registration successfully"
    }


}