package app.vercel.meyssam.classroom.dto.create;

public record CreateUserResponseDto(
        long userId,
        String email,
        String username
) {
}
