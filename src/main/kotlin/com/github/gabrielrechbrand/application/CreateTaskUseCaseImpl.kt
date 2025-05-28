package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository

class CreateTaskUseCaseImpl(
    private val taskRepository: TaskRepository
) : CreateTaskUseCase {

    override fun execute(task: Task): Task {
        return taskRepository.save(task)
    }
}