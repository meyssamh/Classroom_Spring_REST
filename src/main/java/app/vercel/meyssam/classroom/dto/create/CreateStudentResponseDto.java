package app.vercel.meyssam.classroom.dto.create;

public record CreateStudentResponseDto(
        Long studentId,
        String firstName,
        String lastName
) {
}
