package com.github.gabrielrechbrand.`interface`.config

import com.github.gabrielrechbrand.application.CreateTaskUseCase
import com.github.gabrielrechbrand.application.CreateTaskUseCaseImpl
import com.github.gabrielrechbrand.domain.TaskRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    private val taskRepository: TaskRepository
) {
    @Bean
    fun createTaskUseCase(): CreateTaskUseCase = CreateTaskUseCaseImpl(taskRepository)
}
