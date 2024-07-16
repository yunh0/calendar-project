package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.controller.api.BatchController;
import com.yunho.project.calendar.api.dto.EngagementEmailStuff;
import com.yunho.project.calendar.core.domain.entity.Share;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println(stuff.getProps());
    }

    @Override
    public void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq) {
        System.out.println(sendMailBatchReq.toString());
    }

    @Override
    public void sendShareRequestMail(String email, String email1, Share.Direction direction) {
        System.out.println("send share mail");
    }
}
