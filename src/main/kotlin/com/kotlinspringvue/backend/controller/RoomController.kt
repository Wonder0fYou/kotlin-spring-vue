package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.entity.Room
import com.kotlinspringvue.backend.repository.RoomRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8081"])
@RestController
@RequestMapping("/room")
class RoomController (private val roomRepository: RoomRepository){

    @GetMapping
    @Transactional
    fun getAllRooms() : List<Room> = roomRepository.findAll()

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable(value = "id") id: Long) : ResponseEntity<Room> {
        return roomRepository.findById(id).map { room -> ResponseEntity.ok(room) }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    @Transactional
    fun addUser(@RequestBody room: Room): Room = roomRepository.save(room)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable(value = "id") id: Long, @RequestBody room: Room): ResponseEntity<Room> {
        return roomRepository.findById(id).map { existingRoom ->
            existingRoom.nameRoom = room.nameRoom
            ResponseEntity.ok().body(roomRepository.save(existingRoom))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<Unit> {
        return roomRepository.findById(id).map { room ->
            roomRepository.delete(room)
            ResponseEntity<Unit>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}