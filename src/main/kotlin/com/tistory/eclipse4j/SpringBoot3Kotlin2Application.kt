package com.tistory.eclipse4j

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SpringBoot3Kotlin2Application

fun main(args: Array<String>) {
    runApplication<SpringBoot3Kotlin2Application>(*args)
}
