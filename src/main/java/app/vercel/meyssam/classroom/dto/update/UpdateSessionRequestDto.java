package app.vercel.meyssam.classroom.dto.update;

import java.sql.Timestamp;

public record UpdateSessionRequestDto(
        long sessionId,
        Timestamp date,
        String situation
) {
}