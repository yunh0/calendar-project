package com.yunho.project.calendar.core.domain;

import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.User;

public class Notification {

    private Schedule schedule;

    public Notification(Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
