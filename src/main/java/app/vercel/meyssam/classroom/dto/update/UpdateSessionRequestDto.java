package app.vercel.meyssam.classroom.dto.update;

import java.sql.Timestamp;

public record UpdateSessionRequestDto(
        long id,
        Timestamp date,
        String situation
) {
}