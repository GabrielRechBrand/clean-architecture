package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GetTaskUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val getTaskUseCase = GetTaskUseCaseImpl(taskRepository)

    @Test
    fun `should return a task when found`() {
        val task = Task(id = 1L, title = "Test Task", dueDate = LocalDate.now())
        every { taskRepository.findById(1L) } returns task

        val result = getTaskUseCase.execute(1L)

        assertEquals(task, result)
        verify(exactly = 1) { taskRepository.findById(1L) }
    }

    @Test
    fun `should return null when task not found`() {
        every { taskRepository.findById(2L) } returns null

        val result = getTaskUseCase.execute(2L)

        assertNull(result)
        verify(exactly = 1) { taskRepository.findById(2L) }
    }
}