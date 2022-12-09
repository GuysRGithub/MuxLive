package com.guysrobot.muxlive

import com.guysrobot.muxlive.service.FileService
import jakarta.annotation.Resource
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MuxliveApplication(private val fileService: FileService) : CommandLineRunner {
    override fun run(vararg args: String?) {
        fileService.init()
    }
}

fun main(args: Array<String>) {
    runApplication<MuxliveApplication>(*args)
}
