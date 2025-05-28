package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GetAllTasksUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val getAllTasksUseCase = GetAllTasksUseCaseImpl(taskRepository)

    @Test
    fun `should return all tasks`() {
        val mockTasks = listOf(
            Task(id = 1, title = "Task 1", dueDate = LocalDate.now()),
            Task(id = 2, title = "Task 2", dueDate = LocalDate.now().plusDays(1))
        )
        every { taskRepository.findAll() } returns mockTasks

        val result = getAllTasksUseCase.execute()

        assertEquals(mockTasks, result)
        verify(exactly = 1) { taskRepository.findAll() }
    }
}