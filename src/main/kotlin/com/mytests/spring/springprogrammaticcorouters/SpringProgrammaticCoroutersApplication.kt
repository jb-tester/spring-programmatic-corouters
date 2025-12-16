package com.mytests.spring.springprogrammaticcorouters

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(MyBeansRegistrar::class)
class SpringProgrammaticCoroutersApplication

fun main(args: Array<String>) {
    runApplication<SpringProgrammaticCoroutersApplication>(*args)
}
