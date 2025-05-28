package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task

interface GetAllTasksUseCase {
    fun execute(): List<Task>
}