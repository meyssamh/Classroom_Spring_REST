package app.vercel.meyssam.classroom.mapper;

import app.vercel.meyssam.classroom.dto.GetClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class GetClassMapper {

    public GetClassResponseDto toDto(final Class chosenClass) {
        return new GetClassResponseDto(chosenClass.getId(), chosenClass.getClassname());
    }
}