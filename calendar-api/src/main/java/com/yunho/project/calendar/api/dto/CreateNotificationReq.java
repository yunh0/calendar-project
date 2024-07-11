package com.yunho.project.calendar.api.dto;

import com.yunho.project.calendar.core.domain.type.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationReq {
    private String title;
    private LocalDateTime notifyAt;
    private RepeatInfo repeatInfo;

    public List<LocalDateTime> getRepeatTimes() {
        if (repeatInfo == null) {
            return Collections.singletonList(notifyAt);
        }
        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                            long increment = (long) repeatInfo.interval.intervalValue * i;
                            switch (repeatInfo.interval.timeUnit) {
                                case DAY:
                                    return notifyAt.plusDays(increment);
                                case WEEK:
                                    return notifyAt.plusWeeks(increment);
                                case MONTH:
                                    return notifyAt.plusMonths(increment);
                                case YEAR:
                                    return notifyAt.plusYears(increment);
                                default:
                                    throw new RuntimeException("bad request. not matched time unit");
                            }
                        }
                )
                .collect(toList());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepeatInfo {
        private Interval interval;
        private int times;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Interval {
        private int intervalValue;
        private TimeUnit timeUnit;
    }
}
