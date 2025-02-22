package app.vercel.meyssam.classroom.dto.create;

import java.sql.Timestamp;

public record CreateSessionResponseDto(
        long id,
        Timestamp date,
        String situation
) {
}