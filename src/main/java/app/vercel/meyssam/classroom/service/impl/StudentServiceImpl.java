package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.dto.create.CreateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateStudentResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteStudentRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteStudentResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetStudentResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.Student;
import app.vercel.meyssam.classroom.mapper.create.CreateStudentMapper;
import app.vercel.meyssam.classroom.mapper.delete.DeleteStudentMapper;
import app.vercel.meyssam.classroom.mapper.get.GetAllStudentsMapper;
import app.vercel.meyssam.classroom.mapper.get.GetStudentMapper;
import app.vercel.meyssam.classroom.mapper.update.UpdateStudentMapper;
import app.vercel.meyssam.classroom.repository.StudentRepository;
import app.vercel.meyssam.classroom.service.StudentService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final ClassServiceImpl classService;
    private final ClassStudentsServiceImpl classStudentsService;
    private final HistoryLogServiceImpl historyLogService;

    private final CreateStudentMapper createStudentMapper;
    private final UpdateStudentMapper updateStudentMapper;
    private final DeleteStudentMapper deleteStudentMapper;
    private final GetStudentMapper getStudentMapper;
    private final GetAllStudentsMapper getAllStudentsMapper;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            ClassServiceImpl classService,
            ClassStudentsServiceImpl classStudentsService,
            HistoryLogServiceImpl historyLogService,
            CreateStudentMapper createStudentMapper,
            UpdateStudentMapper updateStudentMapper,
            DeleteStudentMapper deleteStudentMapper,
            GetStudentMapper getStudentMapper,
            GetAllStudentsMapper getAllStudentsMapper
    ) {
        this.studentRepository = studentRepository;
        this.classService = classService;
        this.classStudentsService = classStudentsService;
        this.historyLogService = historyLogService;
        this.createStudentMapper = createStudentMapper;
        this.updateStudentMapper = updateStudentMapper;
        this.deleteStudentMapper = deleteStudentMapper;
        this.getStudentMapper = getStudentMapper;
        this.getAllStudentsMapper = getAllStudentsMapper;
    }

    @Override
    public ResponseEntity<GetStudentResponseDto> getStudent(long studentId) {
        Student foundStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Student not found"));

        return ResponseEntity.status(HttpStatus.OK).body(getStudentMapper.toDto(foundStudent));
    }

    @Override
    public ResponseEntity<List<GetStudentResponseDto>> getAllStudents(long classId) {
        List<Student> foundStudents = studentRepository.findByClassId(classId);

        return ResponseEntity.status(HttpStatus.OK).body(getAllStudentsMapper.toDto(foundStudents));
    }

    @Override
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            long classId,
            long userId,
            CreateStudentRequestDto createStudentRequestDto
    ) {
        Class chosenClass = classService.getClassById(classId);

        if (chosenClass == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Student studentToSave = createStudentMapper.toEntity(createStudentRequestDto);

        if (
                studentToSave.getFirstName() == null ||
                        studentToSave.getLastName() == null ||
                        studentToSave.getFirstName().trim().isEmpty() ||
                        studentToSave.getLastName().trim().isEmpty()
        ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentToSave.setCreatedAT(Timestamp.from(Instant.now()));
        studentToSave.setUpdatedAT(Timestamp.from(Instant.now()));

        Student savedStudent = studentRepository.save(studentToSave);

        classStudentsService.createClassStudent(chosenClass, savedStudent);

        historyLogService.saveStudentCreationInHistoryLog(userId, classId, savedStudent.getId());

        return ResponseEntity.status(HttpStatus.OK).body(createStudentMapper.toDto(savedStudent));
    }

    @Override
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            long studentId,
            long classId,
            long userId,
            UpdateStudentRequestDto updateStudentRequestDto
    ) {
        Student studentToUpdate = updateStudentMapper.toEntity(updateStudentRequestDto);

        studentRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Student with id " + studentId + " not found"
                )
        );

        Class classEntity = classService.getClassById(classId);
        if (classEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentToUpdate.setUpdatedAT(Timestamp.from(Instant.now()));

        Student updatedStudent = studentRepository.save(studentToUpdate);

        historyLogService.saveStudentUpdateInHistoryLog(userId, classId, studentId);

        return ResponseEntity.status(HttpStatus.OK).body(updateStudentMapper.toDto(updatedStudent));
    }

    @Override
    public ResponseEntity<DeleteStudentResponseDto> deleteStudent(
            long userId,
            DeleteStudentRequestDto deleteStudentRequestDto
    ) {
        Student studentToDelete = deleteStudentMapper.toEntity(deleteStudentRequestDto);

        if (studentToDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        long classId = classStudentsService.deleteClassStudent(studentToDelete);

        historyLogService.saveStudentDeletionInHistoryLog(userId, classId, studentToDelete.getId());

        return ResponseEntity.status(HttpStatus.OK).body(deleteStudentMapper.toDto(studentToDelete));
    }
}