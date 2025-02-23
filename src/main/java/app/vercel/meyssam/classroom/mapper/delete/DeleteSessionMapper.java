package app.vercel.meyssam.classroom.mapper.delete;

import app.vercel.meyssam.classroom.dto.delete.DeleteSessionRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionResponseDto;
import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.stereotype.Component;

@Component
public class DeleteSessionMapper {

    public Session toEntity(DeleteSessionRequestDto deleteSessionRequestDto) {
        Session sessionToDelete = new Session();

        sessionToDelete.setId(deleteSessionRequestDto.id());
        return sessionToDelete;
    }

    public DeleteSessionResponseDto toDto(Session session) {
        return new DeleteSessionResponseDto(session.getId());
    }
}
