package com.guysrobot.muxlive.repository

import com.guysrobot.muxlive.model.User
import com.guysrobot.muxlive.model.Video
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

}