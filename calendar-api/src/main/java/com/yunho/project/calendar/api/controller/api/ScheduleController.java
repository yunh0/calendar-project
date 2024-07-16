package com.yunho.project.calendar.api.controller.api;

import com.yunho.project.calendar.api.dto.*;
import com.yunho.project.calendar.api.service.*;
import com.yunho.project.calendar.core.domain.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;
    private final ScheduleQueryService scheduleQueryService;
    private final EngagementService engagementService;
    private final ShareService shareService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@Valid  @RequestBody CreateTaskReq createTaskReq,
                                            AuthUser authUser) {
        taskService.create(createTaskReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    public ResponseEntity<Void> createTask(@Valid @RequestBody CreateEventReq createEventReq,
                                            AuthUser authUser) {
        eventService.create(createEventReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> createTask(
            @Valid @RequestBody CreateNotificationReq createNotificationReq, AuthUser authUser) {
        notificationService.create(createNotificationReq, authUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/day")
    public List<ForListScheduleDto> getSchedulesByDay(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return scheduleQueryService.getSchedulesByDay(date == null ? LocalDate.now() : date, authUser);
    }

    @GetMapping("/week")
    public List<ForListScheduleDto> getSchedulesByWeek(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek
    ) {
        return scheduleQueryService.getSchedulesByWeek(startOfWeek == null ? LocalDate.now() : startOfWeek, authUser);
    }

    @GetMapping("/month")
    public List<ForListScheduleDto> getSchedulesByMonth(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") String yearMonth
    ) {
        return scheduleQueryService.getSchedulesByMonth(yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth), authUser);
    }

    @PutMapping("/events/engagements/{engagementId}")
    public RequestStatus updateEngagement(
            @Valid @RequestBody ReplyReq replyReq,
            @PathVariable Long engagementId,
            AuthUser authUser) {
        return engagementService.update(authUser, engagementId, replyReq.getType());
    }

    @PostMapping("/shares")
    public void shareSchedule(
            AuthUser authUser,
            @Valid @RequestBody CreateShareReq req
    ) {
        shareService.createShare(authUser.getId(),
                req.getToUserId(),
                req.getDirection());
    }

    @PutMapping("/shares/{shareId}")
    public void replyToShareRequest(
            @PathVariable Long shareId,
            @Valid @RequestBody ReplyReq replyReq,
            AuthUser authUser
    ) {
        shareService.replyToShareRequest(shareId, authUser.getId(), replyReq.getType());
    }
}
