package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.ClassSessions;
import app.vercel.meyssam.classroom.entity.Session;
import app.vercel.meyssam.classroom.repository.ClassSessionsRepository;
import app.vercel.meyssam.classroom.service.ClassSessionsService;
import org.springframework.stereotype.Service;

@Service
public class ClassSessionsServiceImpl implements ClassSessionsService {

    private final ClassSessionsRepository classSessionsRepository;

    public ClassSessionsServiceImpl(
            ClassSessionsRepository classSessionsRepository
    ) {
        this.classSessionsRepository = classSessionsRepository;
    }

    @Override
    public void createClassSession(Class chosenClass, Session savedSession) {
        ClassSessions classSessions = new ClassSessions();
        classSessions.setClassEntity(chosenClass);
        classSessions.setSession(savedSession);

        classSessionsRepository.save(classSessions);
    }

    @Override
    public long deleteClassSession(Session toDeleteSession) {
        ClassSessions classSessions = classSessionsRepository.findBySessionId(toDeleteSession.getId());

        if (classSessions != null) {
            classSessionsRepository.deleteById(classSessions.getId());
        } else {
            throw new IllegalArgumentException("Record not found");
        }

        return classSessions.getId();
    }
}