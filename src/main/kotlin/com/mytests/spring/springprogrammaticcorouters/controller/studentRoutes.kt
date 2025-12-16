package com.mytests.spring.springprogrammaticcorouters.controller

import org.springframework.web.reactive.function.server.coRouter

    fun studentRoutes(studentHandler: StudentHandler) = coRouter {
        "/api/students".nest {
            GET("/", studentHandler::getAllStudents)
            GET("/{id}", studentHandler::getStudentById)
            POST("/add", studentHandler::createStudent)
            PUT("/{id}", studentHandler::updateStudent)
            DELETE("/{id}", studentHandler::deleteStudent)

            // Enrollment endpoints
            POST("/{studentId}/courses/{courseId}/enroll", studentHandler::enrollInCourse)
            GET("/{studentId}/courses", studentHandler::getStudentCourses)
            POST("/{studentId}/courses/{courseId}/pass-exam", studentHandler::passExam)
        }
    }

