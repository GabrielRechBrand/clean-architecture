package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task

interface GetTaskUseCase {
    fun execute(id: Long): Task?
}