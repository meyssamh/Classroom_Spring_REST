package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.service.impl.UserClassesServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userclasses/")
public class UserClassesRestController {

    private final UserClassesServiceImpl userClassesServiceImpl;

    public UserClassesRestController(UserClassesServiceImpl userClassesServiceImpl) {
        this.userClassesServiceImpl = userClassesServiceImpl;
    }
}