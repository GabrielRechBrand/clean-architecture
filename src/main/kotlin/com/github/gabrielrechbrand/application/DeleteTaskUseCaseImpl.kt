package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.TaskRepository

class DeleteTaskUseCaseImpl(private val taskRepository: TaskRepository) : DeleteTaskUseCase {
    override fun execute(id: Long) = taskRepository.delete(id)
}