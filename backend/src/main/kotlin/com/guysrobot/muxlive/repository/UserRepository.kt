package com.guysrobot.muxlive.repository

import com.guysrobot.muxlive.model.User
import com.guysrobot.muxlive.model.Video
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface UserRepository : MongoRepository<User, String> {
    fun findBySub(sub: String?): Optional<User>

}