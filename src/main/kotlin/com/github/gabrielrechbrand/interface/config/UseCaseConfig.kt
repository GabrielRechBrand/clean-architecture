package com.github.gabrielrechbrand.`interface`.config

import com.github.gabrielrechbrand.application.*
import com.github.gabrielrechbrand.domain.TaskRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    private val taskRepository: TaskRepository
) {
    @Bean fun createTaskUseCase(): CreateTaskUseCase = CreateTaskUseCaseImpl(taskRepository)
    @Bean fun getTaskUseCase(): GetTaskUseCase = GetTaskUseCaseImpl(taskRepository)
    @Bean fun getAllTasksUseCase(): GetAllTasksUseCase = GetAllTasksUseCaseImpl(taskRepository)
    @Bean fun deleteTaskUseCase(): DeleteTaskUseCase = DeleteTaskUseCaseImpl(taskRepository)
    @Bean fun updateTaskUseCase(): UpdateTaskUseCase = UpdateTaskUseCaseImpl(taskRepository)
}
