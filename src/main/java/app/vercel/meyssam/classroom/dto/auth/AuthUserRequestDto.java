package app.vercel.meyssam.classroom.dto.auth;

public record AuthUserRequestDto(
        String username,
        String password
) {
}