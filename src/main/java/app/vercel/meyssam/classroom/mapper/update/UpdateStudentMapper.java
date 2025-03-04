package app.vercel.meyssam.classroom.mapper.update;

import app.vercel.meyssam.classroom.dto.update.UpdateStudentRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateStudentResponseDto;
import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentMapper {

    public Student toEntity(UpdateStudentRequestDto updateStudentRequestDto) {
        Student toUpdateStudent = new Student();

        toUpdateStudent.setId(updateStudentRequestDto.studentId());
        toUpdateStudent.setFirstName(updateStudentRequestDto.firstname());
        toUpdateStudent.setLastName(updateStudentRequestDto.lastname());

        return toUpdateStudent;
    }

    public UpdateStudentResponseDto toDto(Student student) {
        return new UpdateStudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName()
        );
    }
}