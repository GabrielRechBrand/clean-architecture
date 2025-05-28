package com.github.gabrielrechbrand.infrastructure.persistence

import com.github.gabrielrechbrand.domain.Task
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class TaskRepositoryAdapterTest {

    private val jpaTaskRepository = mockk<JpaTaskRepository>()
    private val adapter = TaskRepositoryAdapter(jpaTaskRepository)

    @Test
    fun `should save a task and return saved domain task`() {
        val task = Task(id = null, title = "Test", dueDate = LocalDate.of(2025, 1, 1))
        val entity = TaskEntity(id = 1L, title = "Test", dueDate = LocalDate.of(2025, 1, 1))

        every { jpaTaskRepository.save(any()) } returns entity

        val result = adapter.save(task)

        assertEquals(1L, result.id)
        assertEquals("Test", result.title)
        assertEquals(LocalDate.of(2025, 1, 1), result.dueDate)
    }

    @Test
    fun `should find a task by id and return domain object`() {
        val entity = TaskEntity(id = 1L, title = "Test", dueDate = LocalDate.of(2025, 1, 1))
        every { jpaTaskRepository.findById(1L) } returns Optional.of(entity)

        val result = adapter.findById(1L)

        assertEquals(1L, result?.id)
        assertEquals("Test", result?.title)
        assertEquals(LocalDate.of(2025, 1, 1), result?.dueDate)
    }

    @Test
    fun `should return null when task not found by id`() {
        every { jpaTaskRepository.findById(2L) } returns Optional.empty()

        val result = adapter.findById(2L)

        assertNull(result)
    }

    @Test
    fun `should return all tasks mapped to domain`() {
        val entities = listOf(
            TaskEntity(1L, "Task 1", LocalDate.of(2025, 1, 1)),
            TaskEntity(2L, "Task 2", LocalDate.of(2025, 2, 2))
        )
        every { jpaTaskRepository.findAll() } returns entities

        val result = adapter.findAll()

        assertEquals(2, result.size)
        assertEquals("Task 1", result[0].title)
        assertEquals("Task 2", result[1].title)
    }

    @Test
    fun `should delete task by id`() {
        every { jpaTaskRepository.deleteById(1L) } returns Unit

        adapter.delete(1L)

        verify { jpaTaskRepository.deleteById(1L) }
    }
}
