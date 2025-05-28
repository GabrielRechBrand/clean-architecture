package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository

class GetAllTasksUseCaseImpl(private val taskRepository: TaskRepository) : GetAllTasksUseCase {
    override fun execute(): List<Task> = taskRepository.findAll()
}