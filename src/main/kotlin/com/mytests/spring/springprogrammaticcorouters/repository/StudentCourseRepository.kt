package com.mytests.spring.springprogrammaticcorouters.repository

import com.mytests.spring.springprogrammaticcorouters.entity.StudentCourse
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentCourseRepository : CoroutineCrudRepository<StudentCourse, Long> {
    fun findByStudentId(studentId: Long): Flow<StudentCourse>
    fun findByCourseId(courseId: Long): Flow<StudentCourse>
    suspend fun findByStudentIdAndCourseId(studentId: Long, courseId: Long): StudentCourse?
}
