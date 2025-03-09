package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.dto.create.CreateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateStudentResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteStudentResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetStudentResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentResponseDto;
import app.vercel.meyssam.classroom.service.impl.StudentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentServiceImpl studentService;

    public StudentRestController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{studentId}")
    public ResponseEntity<GetStudentResponseDto> getStudent(
            @PathVariable long studentId
    ) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/classes/{classId}")
    public ResponseEntity<List<GetStudentResponseDto>> getAllStudents(
            @PathVariable long classId
    ) {
        return studentService.getAllStudents(classId);
    }

    @PostMapping("/classes/{classId}/{userId}")
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @PathVariable long classId,
            @PathVariable long userId,
            @RequestBody CreateStudentRequestDto createStudentRequestDto
    ) {
        return studentService.createStudent(classId, userId, createStudentRequestDto);
    }

    @PutMapping("/{classId}/{userId}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            @PathVariable long classId,
            @PathVariable long userId,
            @RequestBody UpdateStudentRequestDto updateStudentRequestDto
    ) {
        return studentService.updateStudent(classId, userId, updateStudentRequestDto);
    }

    @DeleteMapping("/{userId}/{studentId}")
    public ResponseEntity<DeleteStudentResponseDto> deleteStudent(
            @PathVariable long userId,
            @PathVariable long studentId
    ) {
        return studentService.deleteStudent(userId, studentId);
    }
}