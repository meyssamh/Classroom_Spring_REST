package app.vercel.meyssam.classroom.dto.update;

import jakarta.validation.constraints.NotBlank;

public record UpdateStudentRequestDto(
        long studentId,
        @NotBlank(message = "Firstname is required!")
        String firstname,
        @NotBlank(message = "Lastname is required!")
        String lastname
) {
}
