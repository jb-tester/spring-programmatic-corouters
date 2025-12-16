package com.mytests.spring.springprogrammaticcorouters.controller

import com.mytests.spring.springprogrammaticcorouters.entity.Student
import com.mytests.spring.springprogrammaticcorouters.entity.StudentCourse
import com.mytests.spring.springprogrammaticcorouters.repository.StudentCourseRepository
import com.mytests.spring.springprogrammaticcorouters.repository.StudentRepository
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.*
import java.time.LocalDate


class StudentHandler(
    private val studentRepository: StudentRepository,
    private val studentCourseRepository: StudentCourseRepository
) {

     suspend fun getAllStudents(request: ServerRequest): ServerResponse {
        val students = studentRepository.findAll().toList()
        return ServerResponse.ok().bodyValueAndAwait(students)
    }

     suspend fun getStudentById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val student = studentRepository.findById(id)
        return if (student != null) {
            ServerResponse.ok().bodyValueAndAwait(student)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).buildAndAwait()
        }
    }

     suspend fun createStudent(request: ServerRequest): ServerResponse {
        val student = request.awaitBody<Student>()
        val savedStudent = studentRepository.save(student)
        return ServerResponse.status(HttpStatus.CREATED).bodyValueAndAwait(savedStudent)
    }

     suspend fun updateStudent(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val existingStudent = studentRepository.findById(id)
        
        return if (existingStudent != null) {
            val student = request.awaitBody<Student>()
            val updatedStudent = student.copy(id = id)
            val savedStudent = studentRepository.save(updatedStudent)
            ServerResponse.ok().bodyValueAndAwait(savedStudent)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).buildAndAwait()
        }
    }

     suspend fun deleteStudent(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        studentRepository.deleteById(id)
        return ServerResponse.noContent().buildAndAwait()
    }

     suspend fun enrollInCourse(request: ServerRequest): ServerResponse {
        val studentId = request.pathVariable("studentId").toLong()
        val courseId = request.pathVariable("courseId").toLong()
        
        // Check if already enrolled
        val existing = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
        if (existing != null) {
            return ServerResponse.status(HttpStatus.CONFLICT)
                .bodyValueAndAwait(mapOf("message" to "Student already enrolled in this course"))
        }
        
        val enrollment = StudentCourse(
            studentId = studentId,
            courseId = courseId,
            enrollmentDate = LocalDate.now()
        )
        val savedEnrollment = studentCourseRepository.save(enrollment)
        return ServerResponse.status(HttpStatus.CREATED).bodyValueAndAwait(savedEnrollment)
    }

     suspend fun getStudentCourses(request: ServerRequest): ServerResponse {
        val studentId = request.pathVariable("studentId").toLong()
        val courses = studentCourseRepository.findByStudentId(studentId).toList()
        return ServerResponse.ok().bodyValueAndAwait(courses)
    }

     suspend fun passExam(request: ServerRequest): ServerResponse {
        val studentId = request.pathVariable("studentId").toLong()
        val courseId = request.pathVariable("courseId").toLong()
        
        val enrollment = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
        return if (enrollment != null) {
            val body = request.awaitBody<ExamResult>()
            val updated = enrollment.copy(
                examPassed = true,
                grade = body.grade,
                examDate = LocalDate.now()
            )
            val savedEnrollment = studentCourseRepository.save(updated)
            ServerResponse.ok().bodyValueAndAwait(savedEnrollment)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND)
                .bodyValueAndAwait(mapOf("message" to "Enrollment not found"))
        }
    }

    data class ExamResult(val grade: Int)
}




