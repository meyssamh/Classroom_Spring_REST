package app.vercel.meyssam.classroom.mapper.delete;

import app.vercel.meyssam.classroom.dto.delete.DeleteClassRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class DeleteClassMapper {

    public Class toEntity(DeleteClassRequestDto deleteClassRequestDto) {
        final Class toDeleteClass = new Class();

        toDeleteClass.setId(deleteClassRequestDto.id());
        return toDeleteClass;
    }

    public DeleteClassResponseDto toDto(Class deleteClass) {
        return new DeleteClassResponseDto(deleteClass.getId());
    }
}
