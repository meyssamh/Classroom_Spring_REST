package app.vercel.meyssam.classroom.dto.update;

import jakarta.validation.constraints.NotBlank;

public record UpdateClassRequestDto(
        long id,
        @NotBlank(message = "Classname is required!")
        String classname
) {
}
