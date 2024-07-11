package com.yunho.project.calendar.core.domain;

import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.User;

import java.time.LocalDateTime;

public class Task {

    private Schedule schedule;

    public Task (Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
