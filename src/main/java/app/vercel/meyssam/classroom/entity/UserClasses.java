package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_classes")
public class UserClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    User user;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = true)
    Class classEntity;

    public UserClasses() {}

    public UserClasses(long id, User user, Class classEntity) {
        this.id = id;
        this.user = user;
        this.classEntity = classEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Class getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(Class classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public String toString() {
        return "UserClasses{" +
                "id=" + id +
                ", user=" + user +
                ", classEntity=" + classEntity +
                '}';
    }
}