package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task

interface CreateTaskUseCase {
    fun execute(task: Task): Task
}