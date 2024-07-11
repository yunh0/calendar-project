package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.dto.AuthUser;
import com.yunho.project.calendar.api.dto.NotificationCreateReq;
import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.User;
import com.yunho.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.yunho.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(NotificationCreateReq req, AuthUser authUser) {
        final User writer = userService.getOrThrowById(authUser.getId());
        req.getRepeatTimes()
                .forEach(notifyAt ->
                        scheduleRepository.save(Schedule.notification(req.getTitle(), notifyAt, writer)));
    }
}
