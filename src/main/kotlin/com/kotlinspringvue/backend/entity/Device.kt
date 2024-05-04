package com.kotlinspringvue.backend.entity

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "device")
data class Device(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "Model")
    var model: String,

    @Column(name = "Manufacturer")
    var manufacturer: String,

    @Column(name = "SerialNumber")
    var serialNumber: Long,

    @Column(name = "PagePerMinute")
    var pagePerMinute: Long,

    @Column(name = "DateOfReceipt")
    var dateOfReceipt: Date,

    @Column(name = "DateOfCommissioning")
    var dateOfCommissioning: Date,

    @Column(name = "UserId")
    var userId: Long,

    @Column(name = "RoomId")
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
