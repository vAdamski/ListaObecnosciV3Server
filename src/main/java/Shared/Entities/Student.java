package Shared.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Klasa reprezentująca studenta.
 */
@Entity
@Table(name = "student")
public class Student {
    /**
     * Imię studenta.
     */
    private String firstName;
    /**
     * Nazwisko studenta.
     */
    private String lastName;

    @Id
    /**
     * Indeks studenta (klucz główny).
     */
    private String studentIndex;

    /**
     * Identyfikator grupy, do której należy student.
     */
    private Integer groupId; // Identyfikator grupy, do której należy student

    /**
     * Konstruktor domyślny bezparametrowy.
     */
    public Student() {
    }

    /**
     * Konstruktor z parametrami.
     *
     * @param firstName    Imię studenta
     * @param lastName     Nazwisko studenta
     * @param studentIndex Indeks studenta
     * @param groupId      Identyfikator grupy, do której należy student
     */
    public Student(String firstName, String lastName, String studentIndex, Integer groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentIndex = studentIndex;
        this.groupId = groupId;
    }

    /**
     * Zwraca imię studenta.
     *
     * @return Imię studenta
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Ustawia imię studenta.
     *
     * @param firstName Imię studenta
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Zwraca nazwisko studenta.
     *
     * @return Nazwisko studenta
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Ustawia nazwisko studenta.
     *
     * @param lastName Nazwisko studenta
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Zwraca identyfikator grupy, do której należy student.
     *
     * @return Identyfikator grupy
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Ustawia identyfikator grupy, do której należy student.
     *
     * @param groupId Identyfikator grupy
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
