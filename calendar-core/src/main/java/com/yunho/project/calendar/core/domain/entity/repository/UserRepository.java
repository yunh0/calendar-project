package com.yunho.project.calendar.core.domain.entity.repository;

import com.yunho.project.calendar.core.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
