package com.guysrobot.muxlive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MuxliveApplication

fun main(args: Array<String>) {
	runApplication<MuxliveApplication>(*args)
}
