package Shared.Entities;

import javax.persistence.*;

@Entity
@Table(name = "studentgroup")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String groupName;

    public StudentGroup() {
    }

    public StudentGroup(String groupName) {
        this.groupName = groupName;
    }

    public StudentGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    // Getters and setters for the class variables

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
