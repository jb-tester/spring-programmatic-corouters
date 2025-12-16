package com.mytests.spring.springprogrammaticcorouters.repository

import com.mytests.spring.springprogrammaticcorouters.entity.Course
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CoroutineCrudRepository<Course, Long> {
    suspend fun findByCode(code: String): Course?
}
