package app.vercel.meyssam.classroom.mapper.create;

import app.vercel.meyssam.classroom.dto.create.CreateUserRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserResponseDto;
import app.vercel.meyssam.classroom.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserMapper {

    public User toEntity(CreateUserRequestDto createUserRequestDto) {
        User user = new User();

        if(createUserRequestDto.firstname() != null) {
            user.setFirstname(createUserRequestDto.firstname());
        } else {
            user.setFirstname("");
        }

        if(createUserRequestDto.lastname() != null) {
            user.setLastname(createUserRequestDto.lastname());
        } else {
            user.setLastname("");
        }

        user.setEmail(createUserRequestDto.email());
        user.setUsername(createUserRequestDto.username());
        user.setPassword(createUserRequestDto.password());

        return user;
    }

    public CreateUserResponseDto toDto(final User user) {
        return new CreateUserResponseDto(user.getId(), user.getUsername());
    }
}