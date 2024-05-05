package com.kotlinspringvue.backend.entity

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "device")
data class Device(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "model")
    var model: String,

    @Column(name = "manufacturer")
    var manufacturer: String,

    @Column(name = "serial_number")
    var serialNumber: Long,

    @Column(name = "page_per_minute")
    var pagePerMinute: Long,

    @Column(name = "date_of_receipt")
    var dateOfReceipt: Date,

    @Column(name = "date_of_commissioning")
    var dateOfCommissioning: Date,

    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "room_id")
    var roomId: Long,
) {
    constructor() : this(0,
        "",
        "",
        0,
        0,
        Date(0),
        Date(0),
        0,
        0) {

    }
}
