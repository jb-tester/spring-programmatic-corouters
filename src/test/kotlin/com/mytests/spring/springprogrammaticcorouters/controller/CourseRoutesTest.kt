package com.mytests.spring.springprogrammaticcorouters.controller

import com.mytests.spring.springprogrammaticcorouters.entity.Course
import com.mytests.spring.springprogrammaticcorouters.repository.CourseRepository
import com.mytests.spring.springprogrammaticcorouters.repository.StudentCourseRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest
@AutoConfigureWebTestClient
class CourseRoutesTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var studentCourseRepository: StudentCourseRepository


    @Test
    fun `GET all courses should return list of courses`() {

        webTestClient.get()
            .uri("/api/courses/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.length()").isEqualTo(11)

    }
    @Test
    fun `GET all students should return list of students`() {

        webTestClient.get()
            .uri("/api/students/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.length()").isEqualTo(8)

    }

    @Test
    fun `POST create course should return created course with 201 status`() {

        val newCourse = Course(
            name = "Web Development",
            code = "CS310",
            credits = 3
        )

        webTestClient.post()
            .uri("/api/courses/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(newCourse)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(11)
            .jsonPath("$.name").isEqualTo("Web Development")
            .jsonPath("$.code").isEqualTo("CS310")
            .jsonPath("$.credits").isEqualTo(3)


    }

    @Test
    fun `PUT update course should return updated course when course exists`() {

        val updatedCourseData = Course(
            id = 1L,
            name = "Introduction to Kotlin - Updated",
            code = "CS1011",
            credits = 4
        )


        webTestClient.put()
            .uri("/api/courses/1")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedCourseData)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(1)
            .jsonPath("$.name").isEqualTo("Introduction to Kotlin - Updated")
            .jsonPath("$.code").isEqualTo("CS1011")
            .jsonPath("$.credits").isEqualTo(4)


    }

    @Test
    fun `GET all students that got the specific course`() {

        webTestClient.get()
            .uri("/api/courses/2/students")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.length()").isEqualTo(3)

    }

}
