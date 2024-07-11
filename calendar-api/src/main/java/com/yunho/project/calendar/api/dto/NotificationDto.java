package com.yunho.project.calendar.api.dto;

import com.yunho.project.calendar.core.domain.ScheduleType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String title;
    private final LocalDateTime notifyAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}
