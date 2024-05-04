package com.kotlinspringvue.backend.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    var name: String,

    @Column(name = "surname")
    var surname: String,

    @Column(name = "email")
    var email: String
)
{
    // Добавляем конструктор по умолчанию
    constructor() : this(0, "", "", "")
}