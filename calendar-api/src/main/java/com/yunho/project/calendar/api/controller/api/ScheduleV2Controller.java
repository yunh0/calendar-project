package com.yunho.project.calendar.api.controller.api;

import com.yunho.project.calendar.api.dto.AuthUser;
import com.yunho.project.calendar.api.dto.SharedScheduleDto;
import com.yunho.project.calendar.api.service.ScheduleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v2/schedules")
@RestController
public class ScheduleV2Controller {

    private final ScheduleQueryService scheduleQueryService;

    @GetMapping("/day")
    public List<SharedScheduleDto> getSchedulesByDay(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return scheduleQueryService.getSharedSchedulesByDay(date == null ? LocalDate.now() : date, authUser);
    }

    @GetMapping("/week")
    public List<SharedScheduleDto> getSchedulesByWeek(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek
    ) {
        return scheduleQueryService.getSharedSchedulesByWeek(startOfWeek == null ? LocalDate.now() : startOfWeek, authUser);
    }

    @GetMapping("/month")
    public List<SharedScheduleDto> getSchedulesByMonth(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") String yearMonth
    ) {
        return scheduleQueryService.getSharedSchedulesByMonth(yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth), authUser);
    }

}
