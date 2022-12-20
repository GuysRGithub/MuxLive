package com.guysrobot.muxlive.controller

import com.guysrobot.muxlive.dto.CommentDto
import com.guysrobot.muxlive.dto.VideoDto
import com.guysrobot.muxlive.dto.VideoResponse
import com.guysrobot.muxlive.model.Comment
import com.guysrobot.muxlive.service.VideoService
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/videos")
class VideoController(private val videoService: VideoService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun upload(@RequestParam("file") file: MultipartFile): VideoResponse {
        return videoService.upload(file)
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.OK)
    fun uploadThumbnail(@RequestParam("file") file: MultipartFile, @RequestParam("videoId") videoId: String): String {
        return videoService.uploadThumbnail(file, videoId)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun editVideoMetadata(@RequestBody videoDto: VideoDto): VideoDto {
        return videoService.editMetadata(videoDto)
    }

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    fun getVideoDetail(@PathVariable("videoId") id: String): VideoDto {
        return videoService.getVideoDetail(id)
    }

    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.OK)
    fun like(@PathVariable videoId: String): VideoDto? {
        return videoService.like(videoId)
    }

    @PostMapping("/{videoId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    fun dislike(@PathVariable videoId: String): VideoDto? {
        return videoService.dislike(videoId)
    }

    @PostMapping("/{videoId}/comment")
    fun comment(@PathVariable("videoId") videoId: String, @RequestBody commentDto: CommentDto) {
        videoService.addComment(videoId, commentDto)
    }

    @GetMapping("/{videoId}/comments")
    fun comments(@PathVariable("videoId") videoId: String) : List<CommentDto> {
        return videoService.getComments(videoId)
    }

    @GetMapping
    fun videos() : List<VideoDto> {
        return videoService.getVideos()
    }
}