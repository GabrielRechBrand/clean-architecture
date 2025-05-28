package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository

class GetTaskUseCaseImpl(private val taskRepository: TaskRepository) : GetTaskUseCase {
    override fun execute(id: Long): Task? = taskRepository.findById(id);
}