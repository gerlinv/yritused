package com.ng.events.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {
    
    @NotNull String name;
    @NotNull Long maxParticipants;
    @NotNull LocalDateTime startTime;
    @NotNull LocalDateTime endTime;

}
