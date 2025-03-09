package app.vercel.meyssam.classroom.dto.create;

public record CreateUserRequestDto(
        String firstname,
        String lastname,
        String email,
        String username,
        String password
) {
}
