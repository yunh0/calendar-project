package com.yunho.project.calendar.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginReq {

    @NotBlank
    private String email;

    @NotNull
    private String password;
}
