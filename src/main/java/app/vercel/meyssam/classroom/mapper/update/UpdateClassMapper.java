package app.vercel.meyssam.classroom.mapper.update;

import app.vercel.meyssam.classroom.dto.update.UpdateClassRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class UpdateClassMapper {

    public Class toEntity(UpdateClassRequestDto updateClassRequestDto) {
        final Class updateClass = new Class();

        updateClass.setId(updateClassRequestDto.classId());
        updateClass.setClassname(updateClassRequestDto.classname());
        return updateClass;
    }

    public UpdateClassResponseDto toDto(Class updateClass) {
        return new UpdateClassResponseDto(updateClass.getId(), updateClass.getClassname());
    }
}