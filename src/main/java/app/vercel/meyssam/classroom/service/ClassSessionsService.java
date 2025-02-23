package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.Session;

public interface ClassSessionsService {
    void createClassSession(Class chosenClass, Session savedSession);
    long deleteClassSession(Session sessionToDelete);
}
