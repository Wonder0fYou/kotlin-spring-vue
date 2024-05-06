package com.kotlinspringvue.backend.controller

import com.kotlinspringvue.backend.entity.Device
import com.kotlinspringvue.backend.repository.DeviceRepository
import jakarta.servlet.http.HttpServletResponse
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.ByteArrayOutputStream

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
            existingDevice.userId = device.userId
            existingDevice.roomId = device.roomId
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

    @GetMapping("/downloadExcel")
    fun downloadExcel(response: HttpServletResponse) {
        val devices = deviceRepository.findAll()
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Devices")

        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("model")
        headerRow.createCell(1).setCellValue("manufacturer")
        headerRow.createCell(2).setCellValue("serial_number")
        headerRow.createCell(3).setCellValue("page_per_minute")
        headerRow.createCell(4).setCellValue("date_of_receipt")
        headerRow.createCell(5).setCellValue("date_of_commissioning")
        headerRow.createCell(6).setCellValue("user_id")
        headerRow.createCell(7).setCellValue("room_id")

        devices.forEachIndexed { index, device ->
            val row = sheet.createRow(index + 1)
            row.createCell(0).setCellValue(device.model)
            row.createCell(1).setCellValue(device.manufacturer)
            row.createCell(2).setCellValue(device.serialNumber.toDouble())
            row.createCell(3).setCellValue(device.pagePerMinute.toDouble())
            row.createCell(4).setCellValue(device.dateOfReceipt)
            row.createCell(5).setCellValue(device.dateOfCommissioning)
            row.createCell(6).setCellValue(device.userId.toDouble())
            row.createCell(7).setCellValue(device.roomId.toDouble())
        }

        val outputStream = ByteArrayOutputStream()
        workbook.write(outputStream)
        workbook.close()

        response.contentType = "application/vnd.ms-excel"
        response.setHeader("Content-Disposition", "attachment; filename=devices.xlsx")

        response.outputStream.write(outputStream.toByteArray())
    }
}