package com.yunho.project.calendar.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateTaskReq {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDateTime taskAt;

}
