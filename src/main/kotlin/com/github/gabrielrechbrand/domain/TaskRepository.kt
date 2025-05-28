package com.github.gabrielrechbrand.domain

interface TaskRepository {
    fun save(task: Task): Task
    fun findById(id: Long): Task?
    fun findAll(): List<Task>
    fun delete(id: Long)
}