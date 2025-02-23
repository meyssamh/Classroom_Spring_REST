package app.vercel.meyssam.classroom.dto.update;

import java.sql.Timestamp;

public record UpdateSessionResponseDto(
        long id,
        Timestamp date,
        String situation
) {
}