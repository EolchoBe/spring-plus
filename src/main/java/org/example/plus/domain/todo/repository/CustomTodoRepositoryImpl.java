package org.example.plus.domain.todo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.plus.domain.todo.dto.response.QTodoSearchResponse;
import org.example.plus.domain.todo.dto.response.TodoSearchResponse;
import org.example.plus.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.plus.domain.comment.entity.QComment.comment;
import static org.example.plus.domain.manager.entity.QManager.manager;
import static org.example.plus.domain.todo.entity.QTodo.todo;
import static org.example.plus.domain.user.entity.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@Repository
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

    @Override
    public Page<TodoSearchResponse> searchTodos(String title, String managerName, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable) {
        List<TodoSearchResponse> result = queryFactory
                .select(new QTodoSearchResponse(
                        todo.id,
                        todo.title,
                        manager.countDistinct(),
                        comment.countDistinct()
                ))
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(todo.comments, comment)
                .where(
                        containTitle(title),
                        containManagerName(managerName),
                        createdBetween(startDateTime, endDateTime))
                .groupBy(todo.id)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(todo.id.countDistinct())
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(todo.comments, comment)
                .where(
                      containTitle(title),
                      containManagerName(managerName),
                      createdBetween(startDateTime, endDateTime)
                );

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    private BooleanExpression createdBetween(LocalDateTime startDate, LocalDateTime endDate) {
        if(startDate == null && endDate == null){
            return null;
        }
        if (startDate != null && endDate != null){
            return todo.createdAt.between(startDate, endDate);
        }
        if(endDate != null){
            return todo.createdAt.loe(endDate);
        }
        return todo.createdAt.goe(startDate);
    }

    private BooleanExpression containManagerName(String managerName) {
        return hasText(managerName) ? manager.user.nickname.contains(managerName) : null;
    }

    private BooleanExpression containTitle(String title) {
        return hasText(title) ? todo.title.contains(title) : null;
    }
}
