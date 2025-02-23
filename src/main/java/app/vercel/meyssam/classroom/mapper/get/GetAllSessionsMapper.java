package app.vercel.meyssam.classroom.mapper.get;

import app.vercel.meyssam.classroom.dto.get.GetSessionResponseDto;
import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllSessionsMapper {

    public List<GetSessionResponseDto> toDto(final List<Session> sessions) {
        return sessions.stream()
                .map(session -> new GetSessionResponseDto(
                        session.getId(),
                        session.getDate(),
                        session.getSituation()
                ))
                .collect(Collectors.toList());
    }
}
