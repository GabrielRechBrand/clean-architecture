package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository

class UpdateTaskUseCaseImpl(private val taskRepository: TaskRepository) : UpdateTaskUseCase {
    override fun execute(task: Task): Task = taskRepository.save(task)
}