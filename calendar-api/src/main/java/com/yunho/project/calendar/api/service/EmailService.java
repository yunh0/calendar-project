package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement e);
}
