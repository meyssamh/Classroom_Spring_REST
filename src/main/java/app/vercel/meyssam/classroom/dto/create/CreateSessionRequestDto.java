package app.vercel.meyssam.classroom.dto.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateSessionRequestDto(
        @NotBlank(message= "Date is required!")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        LocalDateTime date,
        String situation
) {
}