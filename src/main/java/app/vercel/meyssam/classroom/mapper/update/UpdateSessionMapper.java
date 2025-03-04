package app.vercel.meyssam.classroom.mapper.update;

import app.vercel.meyssam.classroom.dto.update.UpdateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionResponseDto;
import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class UpdateSessionMapper {

    public Session toEntity(UpdateSessionRequestDto updateSessionRequestDto) {
        Session session = new Session();

        session.setId(updateSessionRequestDto.sessionId());
        session.setDate(updateSessionRequestDto.date());
        session.setSituation(updateSessionRequestDto.situation());

        return session;
    }

    public UpdateSessionResponseDto toDto(Session session) {
        return new UpdateSessionResponseDto(
                session.getId(),
                session.getDate(),
                session.getSituation()
        );
    }
}