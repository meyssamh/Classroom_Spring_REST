package app.vercel.meyssam.classroom.dto.update;

public record UpdateStudentResponseDto(
        long studentId,
        String firstname,
        String lastname
) {
}
