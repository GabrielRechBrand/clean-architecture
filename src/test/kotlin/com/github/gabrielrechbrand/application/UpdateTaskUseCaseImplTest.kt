package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class UpdateTaskUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val updateTaskUseCase = UpdateTaskUseCaseImpl(taskRepository)

    @Test
    fun `should update and return the task`() {
        val updatedTask = Task(id = 1L, title = "Updated", dueDate = LocalDate.now())
        every { taskRepository.save(updatedTask) } returns updatedTask

        val result = updateTaskUseCase.execute(updatedTask)

        assertEquals(updatedTask, result)
        verify(exactly = 1) { taskRepository.save(updatedTask) }
    }
}