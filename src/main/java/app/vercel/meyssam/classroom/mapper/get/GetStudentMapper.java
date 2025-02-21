package app.vercel.meyssam.classroom.mapper.get;

import app.vercel.meyssam.classroom.dto.get.GetStudentResponseDto;
import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class GetStudentMapper {

    public GetStudentResponseDto toDto(final Student student) {
        return new GetStudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName()
        );
    }
}
