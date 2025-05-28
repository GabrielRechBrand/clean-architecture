package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.TaskRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeleteTaskUseCaseImplTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var deleteTaskUseCase: DeleteTaskUseCaseImpl

    @BeforeEach
    fun setUp() {
        taskRepository = mockk(relaxed = true)
        deleteTaskUseCase = DeleteTaskUseCaseImpl(taskRepository)
    }

    @Test
    fun `should delegate delete operation to repository`() {
        val taskId = 1L

        deleteTaskUseCase.execute(taskId)

        verify(exactly = 1) { taskRepository.delete(taskId) }
    }
}