package com.yunho.project.calendar.core.domain.entity;

import com.yunho.project.calendar.core.domain.RequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "engagements")
public class Engagement extends BaseEntity{

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}
