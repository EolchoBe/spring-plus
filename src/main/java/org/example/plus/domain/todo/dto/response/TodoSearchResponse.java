package org.example.plus.domain.todo.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TodoSearchResponse {
    private final Long id;
    private final String title;
    private final Long ManagerCount;
    private final Long CommentCount;

    @QueryProjection
    public TodoSearchResponse(Long id, String title, Long managerCount, Long commentCount) {
        this.id = id;
        this.title = title;
        this.ManagerCount = managerCount;
        this.CommentCount = commentCount;
    }
}
