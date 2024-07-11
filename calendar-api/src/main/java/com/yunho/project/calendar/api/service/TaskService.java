package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.dto.AuthUser;
import com.yunho.project.calendar.api.dto.CreateTaskReq;
import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.yunho.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(CreateTaskReq req, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(req.getTitle(), req.getDescription(), req.getTaskAt(), userService.getOrThrowById(authUser.getId()));
        scheduleRepository.save(taskSchedule);
    }

}
