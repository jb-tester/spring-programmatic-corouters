package com.mytests.spring.springprogrammaticcorouters.controller

import com.mytests.spring.springprogrammaticcorouters.entity.Course
import com.mytests.spring.springprogrammaticcorouters.repository.CourseRepository
import com.mytests.spring.springprogrammaticcorouters.repository.StudentCourseRepository
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.*


class CourseHandler(
    private val courseRepository: CourseRepository,
    private val studentCourseRepository: StudentCourseRepository
) {

     suspend fun getAllCourses(request: ServerRequest): ServerResponse {
        val courses = courseRepository.findAll().toList()
        return ServerResponse.ok().bodyValueAndAwait(courses)
    }

     suspend fun getCourseById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val course = courseRepository.findById(id)
        return if (course != null) {
            ServerResponse.ok().bodyValueAndAwait(course)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).buildAndAwait()
        }
    }

     suspend fun createCourse(request: ServerRequest): ServerResponse {
        val course = request.awaitBody<Course>()
        val savedCourse = courseRepository.save(course)
         println(savedCourse.id)
         return ServerResponse.status(HttpStatus.CREATED).bodyValueAndAwait(savedCourse)
    }

     suspend fun updateCourse(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val existingCourse = courseRepository.findById(id)
        
        return if (existingCourse != null) {
            val course = request.awaitBody<Course>()
            val updatedCourse = course.copy(id = id)
            val savedCourse = courseRepository.save(updatedCourse)
            ServerResponse.ok().bodyValueAndAwait(savedCourse)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).buildAndAwait()
        }
    }

     suspend fun deleteCourse(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        courseRepository.deleteById(id)
        return ServerResponse.noContent().buildAndAwait()
    }

     suspend fun getCourseStudents(request: ServerRequest): ServerResponse {
        val courseId = request.pathVariable("courseId").toLong()
        val enrollments = studentCourseRepository.findByCourseId(courseId).toList()
        return ServerResponse.ok().bodyValueAndAwait(enrollments)
    }
}

