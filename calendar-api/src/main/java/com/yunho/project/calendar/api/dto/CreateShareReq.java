package com.yunho.project.calendar.api.dto;

import com.yunho.project.calendar.core.domain.entity.Share;
import lombok.Data;

@Data
public class CreateShareReq {
    private Long toUserId;
    private Share.Direction direction;
}
