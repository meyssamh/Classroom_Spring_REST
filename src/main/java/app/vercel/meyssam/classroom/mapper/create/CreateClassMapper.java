package app.vercel.meyssam.classroom.mapper.create;

import app.vercel.meyssam.classroom.dto.create.CreateClassRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class CreateClassMapper {

    public Class toEntity(CreateClassRequestDto createClassRequestDto) {
        final Class createClass = new Class();

        createClass.setClassname(createClassRequestDto.classname());
        return createClass;
    }

    public CreateClassResponseDto toDto(final Class createdClass) {
        return new CreateClassResponseDto(createdClass.getId(), createdClass.getClassname());
    }
}