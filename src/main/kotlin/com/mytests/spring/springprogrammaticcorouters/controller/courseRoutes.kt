package com.mytests.spring.springprogrammaticcorouters.controller

import org.springframework.web.reactive.function.server.*

// for some reason, i get the false 'not consumed path variables' errors here, though the pathvars usages are found in handler methods???
// there are no errors in the studentRoutes however
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