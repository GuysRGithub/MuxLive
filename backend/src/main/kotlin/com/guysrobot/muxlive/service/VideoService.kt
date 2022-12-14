package com.guysrobot.muxlive.service

import com.guysrobot.muxlive.dto.VideoDto
import com.guysrobot.muxlive.dto.VideoResponse
import com.guysrobot.muxlive.model.Video
import com.guysrobot.muxlive.repository.VideoRepository
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class VideoService(private val service: FileService, private val repository: VideoRepository) {

    fun upload(file: MultipartFile): VideoResponse {
        val path = service.upload(file)
        val video = Video(videoUrl = path)

        val savedVideo = repository.save(video)
        return VideoResponse(videoId = savedVideo.id, videoUrl = savedVideo.videoUrl)
    }

    fun editMetadata(videoDto: VideoDto): VideoDto {
        // Find video by id
        videoDto.id ?: throw IllegalAccessException("Bad request with video dto $videoDto")
        val video = getVideo(videoDto.id)

        // Map fields from dto to video
        val editedVideo = video.copy(
            title = videoDto.title,
            description = videoDto.description,
            tags = videoDto.tags,
            videoUrl = videoDto.videoUrl,
            videoStatus = videoDto.videoStatus,
            thumbnailUrl = videoDto.thumbnailUrl
        )

        // Save video to database
        repository.save(editedVideo)

        return videoDto
    }

    fun uploadThumbnail(file: MultipartFile, videoId: String): String {
        // Find video
        val video = getVideo(videoId)

        val thumbnailUrl = service.upload(file)
        repository.save(video.copy(thumbnailUrl = thumbnailUrl))

        return thumbnailUrl
    }

    private fun getVideo(id: String): Video {
        return repository.findById(id)
            .orElseThrow { IllegalAccessException("Cannot find video by id $id") }
    }

}