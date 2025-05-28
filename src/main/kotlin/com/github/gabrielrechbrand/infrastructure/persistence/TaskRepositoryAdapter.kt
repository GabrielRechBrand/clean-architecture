package com.github.gabrielrechbrand.infrastructure.persistence

import com.github.gabrielrechbrand.domain.Task
import com.github.gabrielrechbrand.domain.TaskRepository
import org.springframework.stereotype.Repository

@Repository
class TaskRepositoryAdapter(
    private val jpaTaskRepository: JpaTaskRepository
) : TaskRepository {

    override fun save(task: Task): Task {
        val entity = TaskEntity(
            id = task.id,
            title = task.title,
            dueDate = task.dueDate
        )
        val saved = jpaTaskRepository.save(entity)
        return Task(
            id = saved.id,
            title = saved.title,
            dueDate = saved.dueDate
        )
    }

    override fun findById(id: Long): Task? =
        jpaTaskRepository.findById(id).map {
            Task(it.id, it.title, it.dueDate)
        }.orElse(null)

    override fun findAll(): List<Task> =
        jpaTaskRepository.findAll().map {
            Task(it.id, it.title, it.dueDate)
        }

    override fun delete(id: Long) =
        jpaTaskRepository.deleteById(id)
}