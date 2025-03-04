package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.dto.create.CreateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateSessionResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetSessionResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessionService {
    ResponseEntity<GetSessionResponseDto> getSession(long sessionId);
    ResponseEntity<List<GetSessionResponseDto>> getAllSessions(long classId);
    ResponseEntity<CreateSessionResponseDto> createSession(
            long userId,
            long classId,
            CreateSessionRequestDto createSessionRequestDto
    );
    ResponseEntity<UpdateSessionResponseDto> updateSession(
            long userId,
            long classId,
            UpdateSessionRequestDto updateSessionRequestDto
    );
    ResponseEntity<DeleteSessionResponseDto> deleteSession(
            long userId,
            long sessionId
    );
}