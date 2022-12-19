package com.guysrobot.muxlive.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class UserInfoDto(
    val id: String?,
    @JsonProperty("sub")
    val sub: String?,
    @JsonProperty("given_name")
    val givenName: String?,
    @JsonProperty("family_name")
    val familyName: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("picture")
    val picture: String?,
    val email: String
)