package com.kotlinspringvue.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "fio")
    var fio: String,

    @Column(name = "email")
    var email: String

)
{
    // Добавляем конструктор по умолчанию
    constructor() : this(1, "", "")
}