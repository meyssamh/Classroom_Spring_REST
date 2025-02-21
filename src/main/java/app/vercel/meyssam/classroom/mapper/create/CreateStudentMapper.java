package app.vercel.meyssam.classroom.mapper.create;

import app.vercel.meyssam.classroom.dto.create.CreateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateStudentResponseDto;
import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentMapper {

    public Student toEntity(CreateStudentRequestDto createStudentRequestDto) {
        Student toSaveStudent = new Student();

        toSaveStudent.setFirstName(createStudentRequestDto.firstname());
        toSaveStudent.setLastName(createStudentRequestDto.lastname());

        return toSaveStudent;
    }

    public CreateStudentResponseDto toDto(Student student) {
        return new CreateStudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName()
        );
    }
}