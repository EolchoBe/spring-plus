package org.example.plus.domain.todo.repository;

import org.example.plus.domain.todo.entity.Todo;

import java.util.Optional;

public interface CustomTodoRepository {

    Optional<Todo> findByIdWithUser(Long todoId);
}
