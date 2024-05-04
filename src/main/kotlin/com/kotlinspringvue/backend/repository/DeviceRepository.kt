package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.entity.Device
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : JpaRepository<Device, Long> {

}