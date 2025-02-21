package app.vercel.meyssam.classroom.dto.get;

public record GetStudentResponseDto(
        long id,
        String firstname,
        String lastname
) {
}