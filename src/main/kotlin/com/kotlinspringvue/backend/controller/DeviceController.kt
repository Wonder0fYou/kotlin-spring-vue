package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.entity.Device
import com.kotlinspringvue.backend.repository.DeviceRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8081"])
@RestController
@RequestMapping("/device")
class DeviceControllerController (private val deviceRepository: DeviceRepository){

    @GetMapping
    @Transactional
    fun getAllRooms() : List<Device> = deviceRepository.findAll()

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable(value = "id") id: Long) : ResponseEntity<Device> {
        return deviceRepository.findById(id).map { device -> ResponseEntity.ok(device) }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    @Transactional
    fun addUser(@RequestBody device: Device): Device = deviceRepository.save(device)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable(value = "id") id: Long, @RequestBody device: Device): ResponseEntity<Device> {
        return deviceRepository.findById(id).map { existingDevice ->
            existingDevice.model = device.model
            existingDevice.manufacturer = device.manufacturer
            existingDevice.serialNumber = device.serialNumber
            existingDevice.pagePerMinute = device.pagePerMinute
            existingDevice.dateOfReceipt = device.dateOfReceipt
            existingDevice.dateOfCommissioning = device.dateOfCommissioning
            existingDevice.paperFormat = device.paperFormat
            existingDevice.printColor = device.printColor
            existingDevice.midDpi = device.midDpi
            existingDevice.userFio = device.userFio
            existingDevice.roomName = device.roomName
            ResponseEntity.ok().body(deviceRepository.save(existingDevice))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<Unit> {
        return deviceRepository.findById(id).map { room ->
            deviceRepository.delete(room)
            ResponseEntity<Unit>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}