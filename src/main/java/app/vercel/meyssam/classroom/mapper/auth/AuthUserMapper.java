package app.vercel.meyssam.classroom.mapper.auth;

import app.vercel.meyssam.classroom.dto.auth.AuthUserRequestDto;
import app.vercel.meyssam.classroom.dto.auth.AuthUserResponseDto;
import app.vercel.meyssam.classroom.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthUserMapper {

    public User toEntity(AuthUserRequestDto authUserRequestDto) {
        final User user = new User();

        user.setUsername(authUserRequestDto.username());
        user.setPassword(authUserRequestDto.password());

        return user;
    }
}