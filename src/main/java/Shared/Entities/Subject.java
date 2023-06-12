package Shared.Entities;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
    private String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(int subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    // Getters and setters for the class variables

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

