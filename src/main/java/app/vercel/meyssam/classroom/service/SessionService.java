package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.dto.create.CreateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateSessionResponseDto;
import org.springframework.http.ResponseEntity;

public interface SessionService {
    ResponseEntity<CreateSessionResponseDto> createSession(CreateSessionRequestDto createSessionRequestDto);
}