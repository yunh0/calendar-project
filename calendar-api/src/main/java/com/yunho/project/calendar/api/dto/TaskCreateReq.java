package com.yunho.project.calendar.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateReq {

    private String title;

    private String description;

    private LocalDateTime taskAt;

}
