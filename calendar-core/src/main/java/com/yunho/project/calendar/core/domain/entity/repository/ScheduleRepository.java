package com.yunho.project.calendar.core.domain.entity.repository;

import com.yunho.project.calendar.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}