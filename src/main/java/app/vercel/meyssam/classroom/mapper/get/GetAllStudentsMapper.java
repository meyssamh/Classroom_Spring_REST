package app.vercel.meyssam.classroom.mapper.get;

import app.vercel.meyssam.classroom.dto.get.GetStudentResponseDto;
import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllStudentsMapper {

    public List<GetStudentResponseDto> toDto(final List<Student> students) {
        return students.stream()
                .map(studentEntity -> new GetStudentResponseDto(
                        studentEntity.getId(),
                        studentEntity.getFirstName(),
                        studentEntity.getLastName()
                ))
                .collect(Collectors.toList());
    }
}