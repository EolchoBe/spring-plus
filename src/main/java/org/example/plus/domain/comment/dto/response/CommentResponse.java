package org.example.plus.domain.comment.dto.response;

import lombok.Getter;
import org.example.plus.domain.user.dto.response.UserResponse;

@Getter
public class CommentResponse {

    private final Long id;
    private final String contents;
    private final UserResponse user;

    public CommentResponse(Long id, String contents, UserResponse user) {
        this.id = id;
        this.contents = contents;
        this.user = user;
    }
}
