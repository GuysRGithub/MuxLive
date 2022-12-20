package com.guysrobot.muxlive.service

import com.guysrobot.muxlive.dto.VideoDto
import com.guysrobot.muxlive.dto.VideoResponse
import com.guysrobot.muxlive.model.Video
import com.guysrobot.muxlive.repository.VideoRepository
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class VideoService(
    private val service: FileService,
    private val repository: VideoRepository,
    private val userService: UserService,
) {

    private fun increaseView(video: Video) {
        video.view()
        repository.save(video)
    }
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

    fun getVideoDetail(id: String): VideoDto {
        val video = getVideo(id)
        // Increase view count
        increaseView(video)
        // Add to history
        userService.addVideoToHistory(id)
        return video.toDTO()
    }

    /**
     * Toggle like the video (if already liked and like again, will remove like)
     * @param videoId: The id of the video, throw exception if the video does not exist
     */
    fun like(videoId: String): VideoDto {
        val video = getVideo(videoId)

        // If user already like video -> decrement like
        if (userService.ifLikedVideo(videoId)) {
            // Unlike
            video.unLike()
            userService.removeFromLikedVideos(videoId)
        } else {
            // If user already dislike video -> decrement dislike and increment like
            if (userService.ifDisLikedVideo(videoId)) {
                // Decrement dislike
                video.unDislike()
                userService.removeFromDisLikedVideos(videoId)
            }
            // Increment like
            video.like()
            userService.addToLikedVideos(videoId)
        }

        // Persist the video
        repository.save(video)

        return video.toDTO()
    }

    /**
     * Toggle dislike the video (if already disliked and dislike again, will remove dislike)
     * @param videoId: The id of the video, throw exception if the video does not exist
     */
    fun dislike(videoId: String): VideoDto {
        val video = getVideo(videoId)

        // If user already dislike video -> decrement dislike (un dislike)
        // If user already like video -> decrement like (unlike) and increment dislike (dislike)
        if (userService.ifLikedVideo(videoId)) {
            video.unDislike()
            userService.removeFromDisLikedVideos(videoId)
        } else {
            // Unlike
            if (userService.ifDisLikedVideo(videoId)) {
                video.unLike()
                userService.removeFromLikedVideos(videoId)
            }
            // Dislike
            video.dislike()
            userService.addToDisLikedVideos(videoId)
        }

        // Persist the video
        repository.save(video)

        return video.toDTO()
    }

}