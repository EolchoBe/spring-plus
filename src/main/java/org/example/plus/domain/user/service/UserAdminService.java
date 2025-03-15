package org.example.plus.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.plus.domain.common.exception.InvalidRequestException;
import org.example.plus.domain.user.dto.request.UserRoleChangeRequest;
import org.example.plus.domain.user.entity.User;
import org.example.plus.domain.user.enums.UserRole;
import org.example.plus.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserRepository userRepository;

    @Transactional
    public void changeUserRole(long userId, UserRoleChangeRequest userRoleChangeRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));
        user.updateRole(UserRole.of(userRoleChangeRequest.getRole()));
    }
}
