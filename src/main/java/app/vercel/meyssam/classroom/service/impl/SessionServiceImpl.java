package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.dto.create.CreateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateSessionResponseDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionRequestDto;
import app.vercel.meyssam.classroom.dto.delete.DeleteSessionResponseDto;
import app.vercel.meyssam.classroom.dto.get.GetSessionResponseDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionRequestDto;
import app.vercel.meyssam.classroom.dto.update.UpdateSessionResponseDto;
import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.Session;
import app.vercel.meyssam.classroom.mapper.create.CreateSessionMapper;
import app.vercel.meyssam.classroom.mapper.delete.DeleteSessionMapper;
import app.vercel.meyssam.classroom.mapper.get.GetAllSessionsMapper;
import app.vercel.meyssam.classroom.mapper.get.GetSessionMapper;
import app.vercel.meyssam.classroom.mapper.update.UpdateSessionMapper;
import app.vercel.meyssam.classroom.repository.SessionRepository;
import app.vercel.meyssam.classroom.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    private final ClassServiceImpl classService;
    private final ClassSessionsServiceImpl classSessionsService;
    private final HistoryLogServiceImpl historyLogService;

    private final GetSessionMapper getSessionMapper;
    private final GetAllSessionsMapper getAllSessionsMapper;
    private final CreateSessionMapper createSessionMapper;
    private final UpdateSessionMapper updateSessionMapper;
    private final DeleteSessionMapper deleteSessionMapper;

    public SessionServiceImpl(
            SessionRepository sessionRepository,
            ClassServiceImpl classService,
            ClassSessionsServiceImpl classSessionsService,
            HistoryLogServiceImpl historyLogService,
            GetSessionMapper getSessionMapper,
            GetAllSessionsMapper getAllSessionsMapper,
            CreateSessionMapper createSessionMapper,
            UpdateSessionMapper updateSessionMapper,
            DeleteSessionMapper deleteSessionMapper
    ) {
        this.sessionRepository = sessionRepository;
        this.classService = classService;
        this.classSessionsService = classSessionsService;
        this.historyLogService = historyLogService;
        this.getSessionMapper = getSessionMapper;
        this.getAllSessionsMapper = getAllSessionsMapper;
        this.createSessionMapper = createSessionMapper;
        this.updateSessionMapper = updateSessionMapper;
        this.deleteSessionMapper = deleteSessionMapper;
    }

    @Override
    public ResponseEntity<GetSessionResponseDto> getSession(long sessionId) {
        Session foundSession = sessionRepository.findById(sessionId).orElseThrow(() ->
                new RuntimeException("Session not found"));

        return ResponseEntity.status(HttpStatus.OK).body(getSessionMapper.toDto(foundSession));
    }

    @Override
    public ResponseEntity<List<GetSessionResponseDto>> getAllSessions(long classId) {
        List<Session> foundSessions = sessionRepository.findByClassId(classId);

        return ResponseEntity.status(HttpStatus.OK).body(getAllSessionsMapper.toDto(foundSessions));
    }

    @Override
    public ResponseEntity<CreateSessionResponseDto> createSession(
            long userId,
            long classId,
            CreateSessionRequestDto createSessionRequestDto
    ) {
        Class chosenClass = classService.getClassById(classId);

        if (chosenClass == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Session sessionToSave = createSessionMapper.toEntity(createSessionRequestDto);

        if (
                sessionToSave.getDate().toString().isEmpty() ||
                        sessionToSave.getSituation().trim().isEmpty() ||
                        sessionToSave.getSituation() == null
        ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sessionToSave.setCreatedAT(Timestamp.from(Instant.now()));
        sessionToSave.setUpdatedAT(Timestamp.from(Instant.now()));

        Session savedSession = sessionRepository.save(sessionToSave);

        classSessionsService.createClassSession(chosenClass, savedSession);

        historyLogService.saveSessionCreationInHistoryLog(userId, classId, savedSession.getId());

        return ResponseEntity.status(HttpStatus.OK).body(createSessionMapper.toDto(savedSession));
    }

    @Override
    public ResponseEntity<UpdateSessionResponseDto> updateSession(
            long userId,
            long classId,
            UpdateSessionRequestDto updateSessionRequestDto
    ) {
        Session sessionToUpdate = updateSessionMapper.toEntity(updateSessionRequestDto);

        sessionRepository.findById(sessionToUpdate.getId()).orElseThrow(() ->
                new RuntimeException("Session with id " + sessionToUpdate.getId() + " not found")
        );

        Class classEntity = classService.getClassById(classId);
        if (classEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sessionToUpdate.setUpdatedAT(Timestamp.from(Instant.now()));

        Session updatedSession = sessionRepository.save(sessionToUpdate);

        historyLogService.saveSessionUpdateInHistoryLog(userId, classId, updatedSession.getId());

        return ResponseEntity.status(HttpStatus.OK).body(updateSessionMapper.toDto(updatedSession));
    }

    @Override
    public ResponseEntity<DeleteSessionResponseDto> deleteSession(
            long userId,
            DeleteSessionRequestDto deleteSessionRequestDto
    ) {
        Session sessionToDelete = deleteSessionMapper.toEntity(deleteSessionRequestDto);

        if (sessionToDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        long classId = classSessionsService.deleteClassSession(sessionToDelete);

        historyLogService.saveSessionDeletionInHistoryLog(userId, classId, sessionToDelete.getId());

        return ResponseEntity.status(HttpStatus.OK).body(deleteSessionMapper.toDto(sessionToDelete));
    }
}