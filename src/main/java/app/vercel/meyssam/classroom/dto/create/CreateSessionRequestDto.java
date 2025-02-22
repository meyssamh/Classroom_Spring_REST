package app.vercel.meyssam.classroom.dto.create;

import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public record CreateSessionRequestDto(
        @NotBlank(message= "Date is required!")
        Timestamp date,
        String situation
) {
}