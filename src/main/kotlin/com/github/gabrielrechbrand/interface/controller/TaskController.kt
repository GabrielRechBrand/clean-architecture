package com.github.gabrielrechbrand.`interface`.controller

import com.github.gabrielrechbrand.application.CreateTaskUseCase
import com.github.gabrielrechbrand.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val createTaskUseCase: CreateTaskUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody request: CreateTaskRequest): Task {
        val task = Task(
            title = request.title,
            dueDate = request.dueDate
        )
        return createTaskUseCase.execute(task)
    }
}
