package app.vercel.meyssam.classroom.mapper;

import app.vercel.meyssam.classroom.dto.DeleteClassRequestDto;
import app.vercel.meyssam.classroom.dto.DeleteClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class DeleteClassMapper {

    public Class toEntity(DeleteClassRequestDto deleteClassRequestDto) {
        final Class deleteClass = new Class();

        deleteClass.setId(deleteClassRequestDto.id());
        return deleteClass;
    }

    public DeleteClassResponseDto toDto(Class deleteClass) {
        return new DeleteClassResponseDto(deleteClass.getId());
    }
}
