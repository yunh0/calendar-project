package com.yunho.project.calendar.api.dto;

import com.yunho.project.calendar.core.domain.type.RequestReplyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReplyEngagementReq {
    @NotNull
    private RequestReplyType type;
}
