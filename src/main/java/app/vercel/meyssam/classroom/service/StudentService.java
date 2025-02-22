package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.dto.create.CreateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateStudentResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteStudentRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteStudentResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetStudentResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<GetStudentResponseDto> getStudent(long studentId);
    ResponseEntity<List<GetStudentResponseDto>> getAllStudents(long classId);
    ResponseEntity<CreateStudentResponseDto> createStudent(
            long classId,
            long userId,
            CreateStudentRequestDto createStudentRequestDto
    );
    ResponseEntity<UpdateStudentResponseDto> updateStudent(
            long studentId,
            long classId,
            long userId,
            UpdateStudentRequestDto updateStudentRequestDto
    );
    ResponseEntity<DeleteStudentResponseDto> deleteStudent(
            long userId,
            DeleteStudentRequestDto deleteStudentRequestDto
    );
}