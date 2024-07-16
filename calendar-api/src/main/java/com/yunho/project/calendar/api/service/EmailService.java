package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.controller.api.BatchController;
import com.yunho.project.calendar.api.dto.EngagementEmailStuff;
import com.yunho.project.calendar.core.domain.entity.Share;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq);
    void sendShareRequestMail(String email, String email1, Share.Direction direction);
}
