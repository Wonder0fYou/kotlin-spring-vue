package com.kotlinspringvue.backend.controller
import com.kotlinspringvue.backend.repository.UserRepository
import com.kotlinspringvue.backend.entity.User
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8081"])
@RestController
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("users")
    @Transactional
    fun getAllUsers(): List<User> = userRepository.findAll()

    @GetMapping("users/{id}")
    fun getUserById(@PathVariable(value = "id") id: Long): ResponseEntity<User> {
        return userRepository.findById(id).map { user -> ResponseEntity.ok(user) }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("users")
    @Transactional
    fun addUser(@RequestBody user: User): User = userRepository.save(user)

    @PutMapping("users/{id}")
    fun updateUser(@PathVariable(value = "id") id: Long, @RequestBody user: User): ResponseEntity<User> {
        return userRepository.findById(id).map { existingUser ->
            existingUser.fio = user.fio
            existingUser.email = user.email
            ResponseEntity.ok().body(userRepository.save(existingUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("users/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<Unit> {
        return userRepository.findById(id).map { user ->
            userRepository.delete(user)
            ResponseEntity<Unit>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}