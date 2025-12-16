package com.mytests.spring.springprogrammaticcorouters

import com.mytests.spring.springprogrammaticcorouters.controller.CourseHandler
import com.mytests.spring.springprogrammaticcorouters.controller.StudentHandler
import com.mytests.spring.springprogrammaticcorouters.controller.courseRoutes
import com.mytests.spring.springprogrammaticcorouters.controller.studentRoutes
import com.mytests.spring.springprogrammaticcorouters.repository.CourseRepository
import org.springframework.beans.factory.BeanRegistrarDsl

class MyBeansRegistrar : BeanRegistrarDsl({
    registerBean<CourseHandler>()
    registerBean(name = "courseRoutes"){ courseRoutes(bean()) }
    registerBean<StudentHandler>()
    registerBean{ studentRoutes(bean()) }
}
)