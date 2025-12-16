package com.mytests.spring.springprogrammaticcorouters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("courses")
data class Course(
    @Id
    val id: Long? = null,
    val name: String,
    val code: String,
    val credits: Int
)
