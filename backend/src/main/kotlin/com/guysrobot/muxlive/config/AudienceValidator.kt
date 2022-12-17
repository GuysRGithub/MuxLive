package com.guysrobot.muxlive.config

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt

class AudienceValidator(private val audience: String) : OAuth2TokenValidator<Jwt> {
    override fun validate(token: Jwt?): OAuth2TokenValidatorResult {
        token ?: return OAuth2TokenValidatorResult.failure(OAuth2Error("Invalid jwt token"))
        if (token.audience.contains(audience)) {
            return OAuth2TokenValidatorResult.success()
        }
        return OAuth2TokenValidatorResult.failure(OAuth2Error("Invalid audience for the given token"))
    }

}
