package org.example.plus.domain.manager.dto.response;

import lombok.Getter;
import org.example.plus.domain.user.dto.response.UserResponse;

@Getter
public class ManagerResponse {

    private final Long id;
    private final UserResponse user;

    public ManagerResponse(Long id, UserResponse user) {
        this.id = id;
        this.user = user;
    }
}
