package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.dto.AuthUser;
import com.yunho.project.calendar.core.domain.RequestStatus;
import com.yunho.project.calendar.core.domain.entity.Engagement;
import com.yunho.project.calendar.core.domain.entity.repository.EngagementRepository;
import com.yunho.project.calendar.core.domain.type.RequestReplyType;
import com.yunho.project.calendar.core.exception.CalendarException;
import com.yunho.project.calendar.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        return engagementRepository.findById(engagementId)
                .filter(Engagement::isRequested)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getStatus();
    }
}
