package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserRestController {

    public final UserServiceImpl userServiceImpl;

    public UserRestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userServiceImpl.saveUser(user);
    }
}