package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAT;

    @Column(name = "updated_at")
    private Timestamp updatedAT;

    @OneToMany(mappedBy = "student")
    Set<ClassStudents> courseStudents;

    public Student() {
    }

    public Student(long id, String firstName, String lastName, Timestamp createdAT, Timestamp updatedAT, Set<ClassStudents> courseStudents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAT = createdAT;
        this.updatedAT = updatedAT;
        this.courseStudents = courseStudents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<ClassStudents> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<ClassStudents> courseStudents) {
        this.courseStudents = courseStudents;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAT=" + createdAT +
                ", updatedAT=" + updatedAT +
                ", courseStudents=" + courseStudents +
                '}';
    }
}