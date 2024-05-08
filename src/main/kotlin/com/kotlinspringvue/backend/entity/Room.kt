package com.kotlinspringvue.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "room")
data class Room(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name_room")
    var nameRoom: String

) {
    constructor() : this(1, "") {

    }
}
