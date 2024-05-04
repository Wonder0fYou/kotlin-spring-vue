package com.kotlinspringvue.backend.repository
import com.kotlinspringvue.backend.entity.User
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface UserRepository : JpaRepository <User, Long>{

}