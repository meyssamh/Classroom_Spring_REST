package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.dto.create.CreateClassRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateClassResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteClassRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteClassResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetClassResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateClassRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassService {
    Class getClassById(long classId);
    ResponseEntity<GetClassResponseDto> getClass(long classId);
    ResponseEntity<List<GetClassResponseDto>> getAllClasses(long userId);
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