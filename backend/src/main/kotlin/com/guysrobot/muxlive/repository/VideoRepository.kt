package com.guysrobot.muxlive.repository

import com.guysrobot.muxlive.model.Video
import org.springframework.data.mongodb.repository.MongoRepository

interface VideoRepository : MongoRepository<Video, String> {

}