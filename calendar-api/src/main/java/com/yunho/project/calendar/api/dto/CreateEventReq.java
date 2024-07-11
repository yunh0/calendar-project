package com.yunho.project.calendar.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateEventReq {
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private List<Long> attendeeIds;
}
