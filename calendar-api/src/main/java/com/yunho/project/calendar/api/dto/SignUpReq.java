package com.yunho.project.calendar.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpReq {

    private String name;

    private String email;

    private String password;

    private LocalDate birthday;

}