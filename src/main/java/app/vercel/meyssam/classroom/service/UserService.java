package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.dto.create.CreateUserRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserResponseDto;
import app.vercel.meyssam.classroom.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<CreateUserResponseDto> saveUser(CreateUserRequestDto createUserRequestDto);
    User getUserById(long userId);
}
