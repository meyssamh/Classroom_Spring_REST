package app.vercel.meyssam.classroom.mapper.get;

import app.vercel.meyssam.classroom.dto.get.GetClassResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class GetClassMapper {

    public GetClassResponseDto toDto(final Class chosenClass) {
        return new GetClassResponseDto(chosenClass.getId(), chosenClass.getClassname());
    }
}