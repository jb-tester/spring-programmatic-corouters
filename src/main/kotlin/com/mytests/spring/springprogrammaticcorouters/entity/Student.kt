package com.mytests.spring.springprogrammaticcorouters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("students")
data class Student(
    @Id
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String
)
