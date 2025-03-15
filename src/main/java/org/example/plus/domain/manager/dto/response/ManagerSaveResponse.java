package org.example.plus.domain.manager.dto.response;

import lombok.Getter;
import org.example.plus.domain.user.dto.response.UserResponse;

@Getter
public class ManagerSaveResponse {

    private final Long id;
    private final UserResponse user;

    public ManagerSaveResponse(Long id, UserResponse user) {
        this.id = id;
        this.user = user;
    }
}
