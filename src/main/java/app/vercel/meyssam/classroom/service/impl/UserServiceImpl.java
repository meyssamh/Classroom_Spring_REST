package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.config.security.SecurityConfig;
import app.vercel.meyssam.classroom.dto.auth.AuthUserRequestDto;
import app.vercel.meyssam.classroom.dto.auth.AuthUserResponseDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserRequestDto;
import app.vercel.meyssam.classroom.dto.create.CreateUserResponseDto;
import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.mapper.auth.AuthUserMapper;
import app.vercel.meyssam.classroom.mapper.create.CreateUserMapper;
import app.vercel.meyssam.classroom.repository.UserRepository;
import app.vercel.meyssam.classroom.service.JwtService;
import app.vercel.meyssam.classroom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final SecurityConfig securityConfig;

    private final UserRepository userRepository;

    private final HistoryLogServiceImpl historyLogService;
    private final JwtService jwtService;

    private final CreateUserMapper createUserMapper;
    private final AuthUserMapper authUserMapper;

    public UserServiceImpl(
            SecurityConfig securityConfig,
            UserRepository userRepository,
            HistoryLogServiceImpl historyLogService,
            JwtService jwtService,
            CreateUserMapper createUserMapper,
            AuthUserMapper authUserMapper
    ) {
        this.securityConfig = securityConfig;
        this.userRepository = userRepository;
        this.historyLogService = historyLogService;
        this.jwtService = jwtService;
        this.createUserMapper = createUserMapper;
        this.authUserMapper = authUserMapper;
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
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

        userToSave.setPassword(securityConfig.passwordEncoder().encode(userToSave.getPassword()));

        userToSave.setCreatedAt(Timestamp.from(Instant.now()));
        userToSave.setUpdatedAt(Timestamp.from(Instant.now()));

        User savedUser = userRepository.save(userToSave);

        historyLogService.saveUserRegistrationInHistoryLog(savedUser.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createUserMapper.toDto(savedUser));
    }

    @Override
    public ResponseEntity<AuthUserResponseDto> authenticate(
            AuthUserRequestDto authUserRequestDto
    ) {
        User user = userRepository.findByUsername(authUserRequestDto.username())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Username or password is incorrect"
                ));

        if (!securityConfig.passwordEncoder().matches(
                authUserRequestDto.password(),
                user.getPassword()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Username or password is incorrect"
            );
        }

        final var token = jwtService.generateToken(user.getUsername(), String.valueOf(user.getId()));

        return ResponseEntity.status(HttpStatus.OK).body(new AuthUserResponseDto(token));
    }
}