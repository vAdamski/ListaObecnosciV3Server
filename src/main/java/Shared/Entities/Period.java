package Shared.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Klasa `Period` reprezentuje termin związany z daną grupą i przedmiotem.
 */
@Entity
@Table(name = "period")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * ID terminu.
     */
    private Integer periodId;
    /**
     * Data terminu.
     */
    private Date date;
    /**
     * Godzina rozpoczęcia terminu.
     */
    private Time startTime;
    /**
     * Godzina zakończenia terminu.
     */
    private Time endTime;
    /**
     * ID grupy związanej z terminem.
     */
    private Integer groupId;
    /**
     * ID przedmiotu związanej z terminem.
     */
    private Integer subjectId;

    /**
     * Konstruktor domyślny bezargumentowy.
     */
    public Period() {
    }

    /**
     * Konstruktor klasy `Period` z parametrami.
     *
     * @param date       Data terminu.
     * @param startTime  Godzina rozpoczęcia terminu.
     * @param endTime    Godzina zakończenia terminu.
     * @param groupId    ID grupy związanej z terminem.
     * @param subjectId  ID przedmiotu związanej z terminem.
     */
    public Period(Date date, Time startTime, Time endTime, Integer groupId, Integer subjectId) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    /**
     * Konstruktor klasy `Period` z parametrami, włączając ID terminu.
     *
     * @param periodId   ID terminu.
     * @param date       Data terminu.
     * @param startTime  Godzina rozpoczęcia terminu.
     * @param endTime    Godzina zakończenia terminu.
     * @param groupId    ID grupy związanej z terminem.
     * @param subjectId  ID przedmiotu związanej z terminem.
     */
    public Period(Integer periodId, Date date, Time startTime, Time endTime, Integer groupId, Integer subjectId) {
        this.periodId = periodId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    /**
     * Metoda getter zwracająca ID terminu.
     *
     * @return ID terminu.
     */
    public Integer getPeriodId() {
        return periodId;
    }

    /**
     * Metoda setter ustawiająca ID terminu.
     *
     * @param periodId ID terminu.
     */
    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    /**
     * Metoda getter zwracająca datę terminu.
     *
     * @return Data terminu.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Metoda setter ustawiająca datę terminu.
     *
     * @param date Data terminu.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Metoda getter zwracająca godzinę rozpoczęcia terminu.
     *
     * @return Godzina rozpoczęcia terminu.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Metoda setter ustawiająca godzinę rozpoczęcia terminu.
     *
     * @param startTime Godzina rozpoczęcia terminu.
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Metoda getter zwracająca godzinę zakończenia terminu.
     *
     * @return Godzina zakończenia terminu.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Metoda setter ustawiająca godzinę zakończenia terminu.
     *
     * @param endTime Godzina zakończenia terminu.
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Metoda getter zwracająca ID grupy związanej z terminem.
     *
     * @return ID grupy związanej z terminem.
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Metoda setter ustawiająca ID grupy związanej z terminem.
     *
     * @param groupId ID grupy związanej z terminem.
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Metoda getter zwracająca ID przedmiotu związanej z terminem.
     *
     * @return ID przedmiotu związanej z terminem.
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Metoda setter ustawiająca ID przedmiotu związanej z terminem.
     *
     * @param subjectId ID przedmiotu związanej z terminem.
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}