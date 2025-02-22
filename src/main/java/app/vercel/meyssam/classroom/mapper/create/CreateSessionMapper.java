package app.vercel.meyssam.classroom.mapper.create;

import app.vercel.meyssam.classroom.dto.create.CreateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateSessionResponseDto;
import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class CreateSessionMapper {

    public Session toEntity(CreateSessionRequestDto createSessionRequestDto) {
        final Session createSession = new Session();

        createSession.setCreatedAT(createSessionRequestDto.date());
        createSession.setSituation(createSessionRequestDto.situation());
        return createSession;
    }

    public CreateSessionResponseDto toDto(Session session) {
        return new CreateSessionResponseDto(session.getId(), session.getDate(), session.getSituation());
    }
}