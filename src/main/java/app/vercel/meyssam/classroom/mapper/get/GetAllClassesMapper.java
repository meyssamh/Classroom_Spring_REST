package app.vercel.meyssam.classroom.mapper.get;

import app.vercel.meyssam.classroom.dto.get.GetClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllClassesMapper {

    public List<GetClassResponseDto> toDto(final List<Class> classes) {
        return classes.stream()
                .map(classEntity -> new GetClassResponseDto(
                        classEntity.getId(),
                        classEntity.getClassname()
                ))
                .collect(Collectors.toList());
    }
}