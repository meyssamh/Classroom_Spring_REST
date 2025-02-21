package app.vercel.meyssam.classroom.mapper;

import app.vercel.meyssam.classroom.dto.UpdateClassRequestDto;
import app.vercel.meyssam.classroom.dto.UpdateClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class UpdateClassMapper {

    public Class toEntity(UpdateClassRequestDto updateClassRequestDto) {
        final Class updateClass = new Class();

        updateClass.setId(updateClassRequestDto.id());
        updateClass.setClassname(updateClassRequestDto.classname());
        return updateClass;
    }

    public UpdateClassResponseDto toDto(Class updateClass) {
        return new UpdateClassResponseDto(updateClass.getId(), updateClass.getClassname());
    }
}