package com.transport_management.service;

import com.transport_management.entity.User;
import com.transport_management.enums.UserRole;
import com.transport_management.enums.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    List<User> getUsersByRole(UserRole role);

    User updateUserStatus(Long userId, UserStatus status);

    List<User> searchUsers(String keyword);
}