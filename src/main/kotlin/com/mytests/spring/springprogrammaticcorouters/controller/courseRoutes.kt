package com.mytests.spring.springprogrammaticcorouters.controller

import org.springframework.web.reactive.function.server.*


fun courseRoutes(courseHandler: CourseHandler) = coRouter {
    "/api/courses".nest {
        GET("/foo") {ServerResponse.ok().bodyValueAndAwait("foo")}
        GET("/") { courseHandler.getAllCourses(it) }
        GET("/{id}") { courseHandler.getCourseById(it) }
        POST("/add") { courseHandler.createCourse(it) }
        PUT("/{id}") { courseHandler.updateCourse(it) }
        DELETE("/{id}") { courseHandler.deleteCourse(it) }
        GET("/{courseId}/students") { courseHandler.getCourseStudents(it) }
    }
}