package com.yunho.project.calendar.api.service;

import com.yunho.project.calendar.api.dto.AuthUser;
import com.yunho.project.calendar.api.dto.CreateEventReq;
import com.yunho.project.calendar.api.dto.EngagementEmailStuff;
import com.yunho.project.calendar.core.domain.RequestStatus;
import com.yunho.project.calendar.core.domain.entity.Engagement;
import com.yunho.project.calendar.core.domain.entity.Schedule;
import com.yunho.project.calendar.core.domain.entity.User;
import com.yunho.project.calendar.core.domain.entity.repository.EngagementRepository;
import com.yunho.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.yunho.project.calendar.core.exception.CalendarException;
import com.yunho.project.calendar.core.exception.ErrorCode;
import com.yunho.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;
    private final EmailService emailService;

    @Transactional
    public void create(CreateEventReq req, AuthUser authUser) {
        // attendees 의 스케쥴 시간과 겹치지 않는지?
        final List<Engagement> engagementList =
                engagementRepository.findAllByAttendeeIdInAndSchedule_EndAtAfter(req.getAttendeeIds(),
                        req.getStartAt());
        if (engagementList
                .stream()
                .anyMatch(e -> e.getEvent().isOverlapped(req.getStartAt(), req.getEndAt())
                        && e.getStatus() == RequestStatus.ACCEPTED)) {
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(), req.getEndAt(), userService.getOrThrowById(authUser.getId()));

        scheduleRepository.save(eventSchedule);

        final List<User> attendeeList = req.getAttendeeIds().stream().map(userService::getOrThrowById).collect(toList());

        attendeeList.forEach(user -> {
            final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
            emailService.sendEngagement(
                    new EngagementEmailStuff(
                            e.getId(),
                            e.getAttendee().getEmail(),
                            attendeeList.stream().map(User::getEmail).collect(toList()),
                            e.getEvent().getTitle(),
                            e.getEvent().getPeriod()
                    ));
        });
    }

}
