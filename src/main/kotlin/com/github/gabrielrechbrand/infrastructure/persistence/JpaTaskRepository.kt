package com.github.gabrielrechbrand.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface JpaTaskRepository : JpaRepository<TaskEntity, Long>