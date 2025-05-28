package com.github.gabrielrechbrand.`interface`.controller

import com.github.gabrielrechbrand.application.*
import com.github.gabrielrechbrand.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody request: CreateTaskRequest): Task {
        val task = Task(title = request.title, dueDate = request.dueDate)
        return createTaskUseCase.execute(task)
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Long): Task? =
        getTaskUseCase.execute(id)

    @GetMapping
    fun getAllTasks(): List<Task> =
        getAllTasksUseCase.execute()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) =
        deleteTaskUseCase.execute(id)

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody request: CreateTaskRequest): Task {
        val task = Task(id = id, title = request.title, dueDate = request.dueDate)
        return updateTaskUseCase.execute(task)
    }
}
