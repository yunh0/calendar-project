package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.controller.api.BatchController;
import com.yunho.project.calendar.api.dto.EngagementEmailStuff;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq);
}
