package com.guysrobot.muxlive.service

import com.guysrobot.muxlive.model.Video
import com.guysrobot.muxlive.repository.VideoRepository
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class VideoService(private val service: FileService, private val repository: VideoRepository) {

    fun upload(file: MultipartFile) {
        val path = service.upload(file)
        val video = Video(videoUrl = path)

        repository.save(video)
    }
}