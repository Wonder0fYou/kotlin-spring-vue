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

    @Column(name = "paper_format")
    var paperFormat: String,

    @Column(name = "print_color")
    var printColor: String,

    @Column(name = "min_dpi")
    var midDpi: String,

    @Column(name = "user_fio")
    var userFio: String,

    @Column(name = "room_name")
    var roomName: String
) {
    constructor() : this(0,
        "",
        "",
        0,
        0,
        Date(0),
        Date(0),
        "",
        "",
        "",
        "",
        "") {
    }
}
