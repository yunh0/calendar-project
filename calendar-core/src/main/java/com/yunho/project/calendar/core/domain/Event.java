package com.yunho.project.calendar.core.domain;

import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.User;

public class Event {

    private Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
