package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.dto.create.CreateUserRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserResponseDto;
import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.mapper.create.CreateUserMapper;
import app.vercel.meyssam.classroom.repository.UserRepository;
import app.vercel.meyssam.classroom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final HistoryLogServiceImpl historyLogService;

    private final CreateUserMapper createUserMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            HistoryLogServiceImpl historyLogService,
            CreateUserMapper createUserMapper
    ) {
        this.userRepository = userRepository;
        this.historyLogService = historyLogService;
        this.createUserMapper = createUserMapper;
    }

    @Override
    public ResponseEntity<CreateUserResponseDto> saveUser(
            CreateUserRequestDto createUserRequestDto
    ) {
        User userToSave = createUserMapper.toEntity(createUserRequestDto);

        if (
                userRepository.existsByEmail(userToSave.getEmail()) ||
                        userRepository.existsByUsername(userToSave.getUsername())
        ) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userToSave.setCreatedAt(Timestamp.from(Instant.now()));
        userToSave.setUpdatedAt(Timestamp.from(Instant.now()));

        User savedUser = userRepository.save(userToSave);

        historyLogService.saveUserRegistrationInHistoryLog(savedUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED  ).body(createUserMapper.toDto(savedUser));
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}