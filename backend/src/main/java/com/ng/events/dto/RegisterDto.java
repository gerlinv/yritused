package com.ng.events.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {

    @NotNull Long eventId;
    @NotNull String firstName;
    @NotNull String lastName;
    @NotNull Long identificationCode;
    
}
