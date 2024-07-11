package com.yunho.project.calendar.api.controller.api;

import com.yunho.project.calendar.api.dto.AuthUser;
import com.yunho.project.calendar.api.dto.EventCreateReq;
import com.yunho.project.calendar.api.dto.NotificationCreateReq;
import com.yunho.project.calendar.api.dto.TaskCreateReq;
import com.yunho.project.calendar.api.service.EventService;
import com.yunho.project.calendar.api.service.NotificationService;
import com.yunho.project.calendar.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq,
                                            AuthUser authUser) {
        taskService.create(taskCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    public ResponseEntity<Void> createTask(@RequestBody EventCreateReq eventCreateReq,
                                            AuthUser authUser) {
        eventService.create(eventCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> createTask(
            @RequestBody NotificationCreateReq notificationCreateReq, AuthUser authUser) {
        notificationService.create(notificationCreateReq, authUser);
        return ResponseEntity.ok().build();
    }
}
