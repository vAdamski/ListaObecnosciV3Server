package Shared.Entities;

import javax.persistence.*;

/**
 * Klasa reprezentująca przedmiot.
 */
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Identyfikator przedmiotu.
     */
    private Integer subjectId;

    /**
     * Nazwa przedmiotu.
     */
    private String name;

    /**
     * Konstruktor domyślny bezparametrowy.
     */
    public Subject() {
    }

    /**
     * Konstruktor z parametrem nazwy przedmiotu.
     *
     * @param name Nazwa przedmiotu
     */
    public Subject(String name) {
        this.name = name;
    }

    /**
     * Konstruktor z parametrami identyfikatora i nazwy przedmiotu.
     *
     * @param subjectId Identyfikator przedmiotu
     * @param name      Nazwa przedmiotu
     */
    public Subject(Integer subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    /**
     * Zwraca identyfikator przedmiotu.
     *
     * @return Identyfikator przedmiotu
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Ustawia identyfikator przedmiotu.
     *
     * @param subjectId Identyfikator przedmiotu
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Zwraca nazwę przedmiotu.
     *
     * @return Nazwa przedmiotu
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę przedmiotu.
     *
     * @param name Nazwa przedmiotu
     */
    public void setName(String name) {
        this.name = name;
    }
}


