package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.dto.create.CreateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateSessionResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetSessionResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionResponseDto;
import app.vercel.meyssam.classroom.service.impl.SessionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionRestController {

    private final SessionServiceImpl sessionService;

    public SessionRestController(
            SessionServiceImpl sessionService
    ) {
        this.sessionService = sessionService;
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<GetSessionResponseDto> getSessionById(
            @PathVariable long sessionId
    ) {
        return sessionService.getSession(sessionId);
    }

    @GetMapping("/{classId}")
    public ResponseEntity<List<GetSessionResponseDto>> getAllSessions(
            @PathVariable long classId
    ) {
        return sessionService.getAllSessions(classId);
    }

    @PostMapping("/class/{classId}/{userId}")
    public ResponseEntity<CreateSessionResponseDto> saveSession(
            @PathVariable long classId,
            @PathVariable long userId,
            @RequestBody CreateSessionRequestDto createSessionRequestDto
    ) {
        return sessionService.createSession(userId, classId, createSessionRequestDto);
    }

    @PutMapping("/{classId}/{userId}")
    public ResponseEntity<UpdateSessionResponseDto> updateSession(
            @PathVariable long classId,
            @PathVariable long userId,
            @RequestBody UpdateSessionRequestDto updateSessionRequestDto
    ) {
        return sessionService.updateSession(userId, classId, updateSessionRequestDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteSessionResponseDto> deleteSession(
            @PathVariable long userId,
            @RequestBody DeleteSessionRequestDto deleteSessionRequestDto
    ) {
        return sessionService.deleteSession(userId, deleteSessionRequestDto);
    }
}