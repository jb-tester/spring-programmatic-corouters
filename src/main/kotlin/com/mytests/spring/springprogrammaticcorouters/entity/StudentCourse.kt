package com.mytests.spring.springprogrammaticcorouters.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("student_courses")
data class StudentCourse(
    @Id
    val id: Long? = null,
    val studentId: Long,
    val courseId: Long,
    val enrollmentDate: LocalDate,
    val examPassed: Boolean = false,
    val grade: Int? = null,
    val examDate: LocalDate? = null
)
