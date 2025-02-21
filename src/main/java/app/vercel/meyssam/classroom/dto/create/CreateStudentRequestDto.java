package app.vercel.meyssam.classroom.dto.create;

import jakarta.validation.constraints.NotBlank;

public record CreateStudentRequestDto(
        @NotBlank(message = "Firstname is required!")
        String firstname,
        @NotBlank(message = "Lastname is required!")
        String lastname
) {
}
