package Shared.Entities;

import javax.persistence.*;

/**
 * Klasa reprezentująca obecność studenta.
 */
@Entity
@Table(name = "presence")
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Identyfikator obecności.
     */
    private Integer presenceId;
    /**
     * Indeks studenta.
     */
    private String studentIndex;
    /**
     * Identyfikator terminu.
     */
    private Integer periodId;
    /**
     * Status obecności.
     */
    private String status;

    /**
     * Konstruktor domyślny bezparametrowy.
     */
    public Presence() {
    }

    /**
     * Konstruktor z parametrami.
     *
     * @param studentIndex Indeks studenta
     * @param periodId     Identyfikator terminu
     * @param status       Status obecności
     * @param presenceId   Identyfikator obecności
     */
    public Presence(String studentIndex, Integer periodId, String status, Integer presenceId) {
        this.studentIndex = studentIndex;
        this.periodId = periodId;
        this.status = status;
        this.presenceId = presenceId;
    }

    /**
     * Zwraca indeks studenta.
     *
     * @return Indeks studenta
     */
    public String getStudentIndex() {
        return studentIndex;
    }

    /**
     * Ustawia indeks studenta.
     *
     * @param studentIndex Indeks studenta
     */
    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    /**
     * Zwraca identyfikator terminu.
     *
     * @return Identyfikator terminu
     */
    public Integer getPeriodId() {
        return periodId;
    }

    /**
     * Ustawia identyfikator terminu.
     *
     * @param periodId Identyfikator terminu
     */
    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    /**
     * Zwraca status obecności.
     *
     * @return Status obecności
     */
    public String getStatus() {
        return status;
    }

    /**
     * Ustawia status obecności.
     *
     * @param status Status obecności
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Zwraca identyfikator obecności.
     *
     * @return Identyfikator obecności
     */
    public Integer getPresenceId() {
        return presenceId;
    }

    /**
     * Ustawia identyfikator obecności.
     *
     * @param presenceId Identyfikator obecności
     */
    public void setPresenceId(Integer presenceId) {
        this.presenceId = presenceId;
    }
}


