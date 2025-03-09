package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.dto.create.CreateClassRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateClassResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteClassResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetClassResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateClassRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.mapper.delete.DeleteClassMapper;
import app.vercel.meyssam.classroom.mapper.get.GetAllClassesMapper;
import app.vercel.meyssam.classroom.mapper.get.GetClassMapper;
import app.vercel.meyssam.classroom.mapper.update.UpdateClassMapper;
import app.vercel.meyssam.classroom.mapper.create.CreateClassMapper;
import app.vercel.meyssam.classroom.repository.ClassRepository;
import app.vercel.meyssam.classroom.service.ClassService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    private final UserClassesServiceImpl userClassesService;
    private final HistoryLogServiceImpl historyLogService;
    private final UserServiceImpl userService;

    private final CreateClassMapper createClassMapper;
    private final UpdateClassMapper updateClassMapper;
    private final DeleteClassMapper deleteClassMapper;
    private final GetClassMapper getClassMapper;
    private final GetAllClassesMapper getAllClassesMapper;

    public ClassServiceImpl(
            ClassRepository classRepository,
            UserClassesServiceImpl userClassesService,
            HistoryLogServiceImpl historyLogService,
            UserServiceImpl userService,
            CreateClassMapper createClassMapper,
            UpdateClassMapper updateClassMapper,
            DeleteClassMapper deleteClassMapper,
            GetClassMapper getClassMapper,
            GetAllClassesMapper getAllClassesMapper
    ) {
        this.classRepository = classRepository;
        this.userClassesService = userClassesService;
        this.historyLogService = historyLogService;
        this.userService = userService;
        this.createClassMapper = createClassMapper;
        this.updateClassMapper = updateClassMapper;
        this.deleteClassMapper = deleteClassMapper;
        this.getClassMapper = getClassMapper;
        this.getAllClassesMapper = getAllClassesMapper;
    }

    @Override
    public Class getClassById(long classId) {
        Class foundClass = classRepository.findClassById(classId);
        if (foundClass == null) {
            throw new ResourceNotFoundException("Class not found");
        }

        return foundClass;
    }

    @Override
    public ResponseEntity<GetClassResponseDto> getClass(long classId) {
        Class foundClass = classRepository.findClassById(classId);
        if (foundClass == null) {
            throw new ResourceNotFoundException("Class not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(getClassMapper.toDto(foundClass));
    }

    @Override
    public ResponseEntity<List<GetClassResponseDto>> getAllClasses(long userId) {
        List<Class> foundClasses = classRepository.findAllClasses(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Classes not found"));

        List<GetClassResponseDto> classDtos = getAllClassesMapper.toDto(foundClasses);

        return ResponseEntity.status(HttpStatus.OK).body(classDtos);
    }

    @Override
    public ResponseEntity<CreateClassResponseDto> createClass(
            long userId,
            CreateClassRequestDto createClassRequestDto
    ) {
        if (
                createClassRequestDto.classname() == null ||
                        createClassRequestDto.classname().trim().isEmpty()
        ) {
            throw new IllegalArgumentException("Classname cannot be null or empty");
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Class classToSave = createClassMapper.toEntity(createClassRequestDto);

        classToSave.setCreatedAt(Timestamp.from(Instant.now()));
        classToSave.setUpdatedAt(Timestamp.from(Instant.now()));

        Class savedClass = classRepository.save(classToSave);

        userClassesService.createUserClasses(user, savedClass);

        historyLogService.saveClassCreationInHistoryLog(userId, savedClass.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createClassMapper.toDto(savedClass));
    }

    @Override
    public ResponseEntity<UpdateClassResponseDto> updateClass(
            long userId,
            UpdateClassRequestDto updateClassRequestDto
    ) {
        Class classToUpdate = updateClassMapper.toEntity(updateClassRequestDto);

        Class foundClass = classRepository.findClassById(classToUpdate.getId());
        if (foundClass == null) {
            throw new ResourceNotFoundException("Class with not found!");
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        classToUpdate.setCreatedAt(foundClass.getCreatedAt());
        classToUpdate.setUpdatedAt(Timestamp.from(Instant.now()));

        Class updatedClass = classRepository.save(classToUpdate);

        historyLogService.saveClassUpdateInHistoryLog(userId, classToUpdate.getId());

        return ResponseEntity.status(HttpStatus.OK).body(updateClassMapper.toDto(updatedClass));
    }

    @Override
    public ResponseEntity<DeleteClassResponseDto> deleteClass(
            long userId,
            long classId
    ) {
        Class classToDelete = classRepository.findClassById(classId);
        if (classToDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userClassesService.deleteUserClasses(classToDelete);

        classRepository.deleteById(classId);

        historyLogService.saveClassDeletionInHistoryLog(userId, classId);

        return ResponseEntity.status(HttpStatus.OK).body(deleteClassMapper.toDto(classToDelete));
    }
}