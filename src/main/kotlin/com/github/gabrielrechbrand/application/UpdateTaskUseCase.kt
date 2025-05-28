package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task

interface UpdateTaskUseCase {
    fun execute(task: Task): Task
}