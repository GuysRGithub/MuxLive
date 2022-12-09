package com.guysrobot.muxlive.service

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.UUID
import java.util.stream.Stream


@Service
class LocalService : FileService {

    private val root: Path = Paths.get("uploads")

    override fun init() {
        try {
            Files.createDirectories(root)
        } catch (e: IOException) {
            throw RuntimeException("Could not initialize folder for upload!")
        }
    }

    override fun upload(file: MultipartFile): String {
        val ext = StringUtils.getFilenameExtension(file.originalFilename)
        val fileName = "${UUID.randomUUID()}.${ext}"
        try {
            Files.copy(file.inputStream, this.root.resolve(fileName))
        } catch (e: Exception) {
            if (e is FileAlreadyExistsException) {
                throw RuntimeException("A file of that name already exists.")
            }

            throw RuntimeException(e.message)
        }
        return fileName
    }

    override fun load(fileName: String): Resource {
        try {
            val file = root.resolve(fileName)
            val resource = UrlResource(file.toUri())

            if (resource.exists() || resource.isReadable) {
                return resource
            } else {
                throw RuntimeException("Could not read the file")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("Error: ${e.message}")
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile())
    }

    override fun loadAll(): Stream<Path> {
        try {
            return Files.walk(root, 1).filter { path -> !path.equals(root) }.map { root.relativize(it) }
        } catch (e: IOException) {
            throw RuntimeException("Could not load the files")
        }
    }
}