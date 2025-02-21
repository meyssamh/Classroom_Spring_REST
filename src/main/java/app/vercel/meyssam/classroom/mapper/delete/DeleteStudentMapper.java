package app.vercel.meyssam.classroom.mapper.delete;

import app.vercel.meyssam.classroom.dto.delete.DeleteStudentRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteStudentResponseDto;
import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class DeleteStudentMapper {

    public Student toEntity(DeleteStudentRequestDto deleteStudentRequestDto) {
        final Student toDeleteStudent = new Student();

        toDeleteStudent.setId(deleteStudentRequestDto.studentId());
        return toDeleteStudent;
    }

    public DeleteStudentResponseDto toDto(Student student) {
        return new DeleteStudentResponseDto(student.getId());
    }
}