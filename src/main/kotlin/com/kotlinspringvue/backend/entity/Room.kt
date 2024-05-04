package com.kotlinspringvue.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "room")
data class Room(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "NameRoom")
    var nameRoom: String
) {
    constructor() : this(0,"") {

    }
}
