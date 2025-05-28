package com.github.gabrielrechbrand.`interface`.controller

import com.github.gabrielrechbrand.application.*
import com.github.gabrielrechbrand.domain.Task
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

@WebMvcTest(TaskController::class)
class TaskControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var createTaskUseCase: CreateTaskUseCase

    @MockkBean
    lateinit var getTaskUseCase: GetTaskUseCase

    @MockkBean
    lateinit var getAllTasksUseCase: GetAllTasksUseCase

    @MockkBean
    lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @MockkBean
    lateinit var updateTaskUseCase: UpdateTaskUseCase

    @Test
    fun `POST createTask should return created task with status 201`() {
        val task = Task(id = 1, title = "Test Task", dueDate = LocalDate.of(2025, 5, 28))
        every { createTaskUseCase.execute(any()) } returns task

        val json = """{"title":"Test Task","dueDate":"2025-05-28"}"""

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("Test Task"))
            .andExpect(jsonPath("$.dueDate").value("2025-05-28"))

        verify { createTaskUseCase.execute(match { it.title == "Test Task" }) }
    }

    @Test
    fun `GET getTask should return task for given id`() {
        val task = Task(id = 2, title = "My Task", dueDate = LocalDate.of(2025, 1, 1))
        every { getTaskUseCase.execute(2) } returns task

        mockMvc.perform(get("/tasks/2"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.title").value("My Task"))
            .andExpect(jsonPath("$.dueDate").value("2025-01-01"))

        verify { getTaskUseCase.execute(2) }
    }

    @Test
    fun `GET getTask should return 200 with empty body when task not found`() {
        every { getTaskUseCase.execute(999) } returns null

        mockMvc.perform(get("/tasks/999"))
            .andExpect(status().isOk)
            .andExpect(content().string(""))

        verify { getTaskUseCase.execute(999) }
    }

    @Test
    fun `GET getAllTasks should return list of tasks`() {
        val tasks = listOf(
            Task(id = 1, title = "Task 1", dueDate = null),
            Task(id = 2, title = "Task 2", dueDate = LocalDate.of(2025, 5, 30))
        )
        every { getAllTasksUseCase.execute() } returns tasks

        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(tasks.size))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].title").value("Task 1"))
            .andExpect(jsonPath("$[1].dueDate").value("2025-05-30"))

        verify { getAllTasksUseCase.execute() }
    }

    @Test
    fun `DELETE deleteTask should return no content`() {
        every { deleteTaskUseCase.execute(3) } returns Unit

        mockMvc.perform(delete("/tasks/3"))
            .andExpect(status().isNoContent)

        verify { deleteTaskUseCase.execute(3) }
    }

    @Test
    fun `PUT updateTask should update and return updated task`() {
        val updatedTask = Task(id = 4, title = "Updated Task", dueDate = LocalDate.of(2025, 12, 31))
        every { updateTaskUseCase.execute(any()) } returns updatedTask

        val json = """{"title":"Updated Task","dueDate":"2025-12-31"}"""

        mockMvc.perform(put("/tasks/4")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(4))
            .andExpect(jsonPath("$.title").value("Updated Task"))
            .andExpect(jsonPath("$.dueDate").value("2025-12-31"))

        verify { updateTaskUseCase.execute(match { it.id == 4L && it.title == "Updated Task" }) }
    }
}