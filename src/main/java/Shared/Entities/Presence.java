package Shared.Entities;

import javax.persistence.*;

@Entity
@Table(name = "presence")
public class Presence {
    @Id
    private Integer presenceId;
    private String studentIndex;
    private int periodId;
    private String status;

    public Presence() {
    }

    public Presence(String studentIndex, int periodId, String status, Integer presenceId) {
        this.studentIndex = studentIndex;
        this.periodId = periodId;
        this.status = status;
        this.presenceId = presenceId;
    }

    public String getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPresenceId() {
        return presenceId;
    }

    public void setPresenceId(Integer presenceId) {
        this.presenceId = presenceId;
    }
}

