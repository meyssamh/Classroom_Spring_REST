package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassService {
    ResponseEntity<GetClassResponseDto> getClass(Long classId);
    ResponseEntity<List<GetClassResponseDto>> getAllClasses(Long userId);
    ResponseEntity<CreateClassResponseDto> createClass(
            long userId,
            CreateClassRequestDto createClassRequestDto
    );
    ResponseEntity<UpdateClassResponseDto> updateClass(
            long userId,
            UpdateClassRequestDto updateClassRequestDto
    );
    ResponseEntity<DeleteClassResponseDto> deleteClass(
            long userId,
            DeleteClassRequestDto deleteClassRequestDto
    );
}