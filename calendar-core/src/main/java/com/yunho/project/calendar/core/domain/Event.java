package com.yunho.project.calendar.core.domain;

import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.User;

import java.time.LocalDateTime;

public class Event {

    private final Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return this.schedule.getId();
    }
    public LocalDateTime getStartAt() {
        return schedule.getStartAt();
    }

    public LocalDateTime getEndAt() {
        return schedule.getEndAt();
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && startAt.isBefore(this.getEndAt());
    }
}
