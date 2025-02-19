package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.service.UserClassesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userclasses/")
public class UserClassesRestController {

    private final UserClassesService userClassesService;

    public UserClassesRestController(UserClassesService userClassesService) {
        this.userClassesService = userClassesService;
    }

    @GetMapping("/sdf/{userId}")
    public ResponseEntity<List<app.vercel.meyssam.classroom.entity.Class>> getClasses(@PathVariable long userId) {
        List<Class> classes = userClassesService.findClassesByUserId(userId);
        return ResponseEntity.ok(classes);
    }
}