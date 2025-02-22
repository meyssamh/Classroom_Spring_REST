package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAT;

    @Column(name = "updated_at")
    private Timestamp updatedAT;

    @Column(nullable = false)
    private Timestamp date;

    private String situation;

    @OneToMany(mappedBy = "session")
    Set<ClassSessions> courseSessions;

    public Session() {
    }

    public Session(
            long id,
            Timestamp createdAT,
            Timestamp updatedAT,
            Timestamp date,
            String situation,
            Set<ClassSessions> courseSessions
    ) {
        this.id = id;
        this.createdAT = createdAT;
        this.updatedAT = updatedAT;
        this.date = date;
        this.situation = situation;
        this.courseSessions = courseSessions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(Timestamp createdAT) {
        this.createdAT = createdAT;
    }

    public Timestamp getUpdatedAT() {
        return updatedAT;
    }

    public void setUpdatedAT(Timestamp updatedAT) {
        this.updatedAT = updatedAT;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Set<ClassSessions> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(Set<ClassSessions> courseSessions) {
        this.courseSessions = courseSessions;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", createdAT=" + createdAT +
                ", updatedAT=" + updatedAT +
                ", date=" + date +
                ", situation='" + situation + '\'' +
                ", courseSessions=" + courseSessions +
                '}';
    }
}