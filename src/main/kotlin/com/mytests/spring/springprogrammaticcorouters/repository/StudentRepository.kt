package com.mytests.spring.springprogrammaticcorouters.repository

import com.mytests.spring.springprogrammaticcorouters.entity.Student
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CoroutineCrudRepository<Student, Long> {
    suspend fun findByEmail(email: String): Student?
}
