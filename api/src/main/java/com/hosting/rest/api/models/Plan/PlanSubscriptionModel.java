package com.hosting.rest.api.models.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PLAN_SUBSCRIPTION")
public class PlanSubscriptionModel {

    @EmbeddedId
    private PlanSubscriptionUserHostId planSubscriptionUserHostId;
    
    @Column(name = "CREATED_AT")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;
}
