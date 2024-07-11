package com.yunho.project.calendar.core.domain.entity.repository;

import com.yunho.project.calendar.core.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
