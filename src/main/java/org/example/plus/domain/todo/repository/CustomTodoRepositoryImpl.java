package org.example.plus.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.plus.domain.todo.entity.Todo;

import java.util.Optional;

import static org.example.plus.domain.todo.entity.QTodo.todo;
import static org.example.plus.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class CustomTodoRepositoryImpl implements CustomTodoRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo result = queryFactory
                .selectFrom(todo)
                .leftJoin(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
