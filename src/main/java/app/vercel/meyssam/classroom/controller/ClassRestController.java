package app.vercel.meyssam.classroom.controller;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.HistoryLog;
import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.entity.UserClasses;
import app.vercel.meyssam.classroom.service.ClassService;
import app.vercel.meyssam.classroom.service.HistoryLogService;
import app.vercel.meyssam.classroom.service.UserClassesService;
import app.vercel.meyssam.classroom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassRestController {

    private final ClassService classService;
    private final UserService userService;
    private final UserClassesService userClassesService;
    private final HistoryLogService historyLogService;

    public ClassRestController(ClassService classService, UserService userService, UserClassesService userClassesService, HistoryLogService historyLogService) {
        this.classService = classService;
        this.userService = userService;
        this.userClassesService = userClassesService;
        this.historyLogService = historyLogService;
    }

    @GetMapping("/{classId}")
    public ResponseEntity<Class> getClassById(@PathVariable long classId) {
        Optional<Class> foundClass = classService.findById(classId);
        return foundClass.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<Class> saveClass(@PathVariable long userId,@RequestBody Class classToSave) {
        if (classToSave.getClassname() == null || classToSave.getClassname().trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty");
        }

        Class savedClass = classService.saveClass(classToSave);

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        UserClasses userClasses = new UserClasses();
        userClasses.setUser(user);
        userClasses.setClassEntity(savedClass);

        userClassesService.save(userClasses);

        HistoryLog historyLog = new HistoryLog();
        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId + " saved class with id " + savedClass.getId());
        historyLog.setuserId(userId);

        historyLogService.saveHistoryLog(historyLog);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedClass);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<Class> updateClass(@PathVariable long classId, @RequestBody Class classToUpdate) {
        Class updatedClass = classService.updateClass(classId, classToUpdate);
        return ResponseEntity.ok(updatedClass);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable long classId) {
        userClassesService.deleteRecordByClassId(classId);
        classService.deleteClass(classId);
        return ResponseEntity.noContent().build();
    }
}