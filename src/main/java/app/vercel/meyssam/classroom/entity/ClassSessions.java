package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "class_sessions")
public class ClassSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = true)
    Session session;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = true)
    Class classEntity;

    public ClassSessions() {}

    public ClassSessions(long id, Session session, Class classEntity) {
        this.id = id;
        this.session = session;
        this.classEntity = classEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Class getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(Class classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public String toString() {
        return "ClassSessions{" +
                "id=" + id +
                ", session=" + session +
                ", classEntity=" + classEntity +
                '}';
    }
}