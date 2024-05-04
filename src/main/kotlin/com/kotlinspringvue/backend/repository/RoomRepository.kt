package com.kotlinspringvue.backend.repository

import com.kotlinspringvue.backend.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : JpaRepository<Room, Long> {

}