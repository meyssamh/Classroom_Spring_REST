package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.dto.auth.AuthUserRequestDto;
import app.vercel.meyssam.classroom.dto.auth.AuthUserResponseDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserResponseDto;
import app.vercel.meyssam.classroom.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserRestController {

    public final UserServiceImpl userService;

    public UserRestController(
            UserServiceImpl userService
    ) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> signup(
            @RequestBody CreateUserRequestDto user
    ) {
        return userService.saveUser(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthUserResponseDto> signin(
            @RequestBody AuthUserRequestDto user
    ) {
        return userService.authenticate(user);
    }
}