package com.guysrobot.muxlive.service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface FileService {
    fun upload(file: MultipartFile) : String
    fun init()
    fun load(fileName: String): Resource
    fun loadAll(): Stream<Path>
    fun deleteAll()
}