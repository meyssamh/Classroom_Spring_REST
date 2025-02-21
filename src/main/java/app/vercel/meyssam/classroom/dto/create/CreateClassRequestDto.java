package app.vercel.meyssam.classroom.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateClassRequestDto(
        @NotBlank(message = "Classname is required!")
        String classname
) {
}