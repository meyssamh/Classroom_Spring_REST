package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="history_log")
public class HistoryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String activity;

    public HistoryLog() {
    }

    public HistoryLog(
            long id,
            Timestamp createdAt,
            long userId,
            String activity
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.activity = activity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public long getuserId() {
        return userId;
    }

    public void setuserId(long userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HistoryLog{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", activity='" + activity + '\'' +
                '}';
    }
}