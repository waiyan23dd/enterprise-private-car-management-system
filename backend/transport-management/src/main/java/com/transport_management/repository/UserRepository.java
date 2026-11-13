package com.transport_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport_management.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndDeletedAtIsNull(String email);

    Optional<User> findByUsernameAndDeletedAtIsNull(String username);

    List<User> findAllByDeletedAtIsNull();

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}