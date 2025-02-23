package app.vercel.meyssam.classroom.mapper.get;

import app.vercel.meyssam.classroom.dto.get.GetSessionResponseDto;
import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class GetSessionMapper {
    public GetSessionResponseDto toDto(Session session) {
        return new GetSessionResponseDto(
                session.getId(),
                session.getDate(),
                session.getSituation()
        );
    }
}
