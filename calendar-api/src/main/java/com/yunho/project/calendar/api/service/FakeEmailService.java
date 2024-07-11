package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.core.domain.entity.Engagement;
import org.springframework.stereotype.Service;

@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendEngagement(Engagement e) {
        System.out.println("메일발송 - attendee: " + e.getAttendee().getEmail() + ", scheduleId: " + e.getEvent().getId());
    }
}
