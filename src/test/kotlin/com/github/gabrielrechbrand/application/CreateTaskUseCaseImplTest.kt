package com.github.gabrielrechbrand.application

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class CreateTaskUseCaseImplTest {

    @Mock
    private lateinit var taskRepository: TaskRepository
    private lateinit var createTaskUseCase: CreateTaskUseCaseImpl

    @BeforeEach
    fun setup() {
        createTaskUseCase = CreateTaskUseCaseImpl(taskRepository)
    }

    @Test
    fun `should save task using repository`() {
        val task = Task(title = "Test", dueDate = null)
        val savedTask = task.copy(id = 1)

        whenever(taskRepository.save(task)).thenReturn(savedTask)

        val result = createTaskUseCase.execute(task)

        assertEquals(savedTask, result)
        verify(taskRepository).save(task)
    }
}