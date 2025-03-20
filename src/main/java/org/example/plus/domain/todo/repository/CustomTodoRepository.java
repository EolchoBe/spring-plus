package org.example.plus.domain.todo.repository;

import org.example.plus.domain.todo.dto.response.TodoSearchResponse;
import org.example.plus.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CustomTodoRepository {

    Optional<Todo> findByIdWithUser(Long todoId);
    Page<TodoSearchResponse> searchTodos(String title, String managerName, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
