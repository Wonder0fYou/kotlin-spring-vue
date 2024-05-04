package com.kotlinspringvue.backend

import com.kotlinspringvue.backend.entity.User
import com.kotlinspringvue.backend.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EntityScan(basePackages = ["com.kotlinspringvue.backend.entity"])
class BackendApplication (private val userRepository: UserRepository) {


	fun run () {
		val users: List<User> = userRepository.findAll()
		users.forEach { println(it) }
	}
}

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}