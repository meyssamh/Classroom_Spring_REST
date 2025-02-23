package app.vercel.meyssam.classroom.dto.get;

import java.sql.Timestamp;

public record GetSessionResponseDto(
        long id,
        Timestamp date,
        String Situation
) {
}
