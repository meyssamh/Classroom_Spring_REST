package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.dto.*;
import app.vercel.meyssam.classroom.service.impl.ClassServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassRestController {

    private final ClassServiceImpl classService;

    public ClassRestController(
            ClassServiceImpl classService
    ) {
        this.classService = classService;
    }

    @GetMapping("/{classId}")
    public ResponseEntity<GetClassResponseDto> getClassById(@PathVariable long classId) {
        return classService.getClass(classId);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<GetClassResponseDto>> getAllClasses(@PathVariable long userId) {
        return classService.getAllClasses(userId);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<CreateClassResponseDto> saveClass(
            @PathVariable long userId,
            @RequestBody CreateClassRequestDto createClassRequestDto
    ) {
        return classService.createClass(userId, createClassRequestDto);
    }

    @PutMapping("/{classId}/{userId}")
    public ResponseEntity<UpdateClassResponseDto> updateClass(
            @PathVariable long classId,
            @PathVariable long userId,
            @RequestBody UpdateClassRequestDto updateClassRequestDto
    ) {
        return classService.updateClass(userId, updateClassRequestDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteClassResponseDto> deleteClass(
            @PathVariable long userId,
            @RequestBody DeleteClassRequestDto deleteClassRequestDto
    ) {
        return classService.deleteClass(userId, deleteClassRequestDto);
    }
}