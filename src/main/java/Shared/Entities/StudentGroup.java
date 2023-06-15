package Shared.Entities;

import javax.persistence.*;

/**
 * Klasa reprezentująca grupę studentów.
 */
@Entity
@Table(name = "studentgroup")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Identyfikator grupy.
     */
    private Integer groupId;

    /**
     * Nazwa grupy.
     */
    private String groupName;

    /**
     * Konstruktor domyślny bezparametrowy.
     */
    public StudentGroup() {
    }

    /**
     * Konstruktor z parametrem nazwy grupy.
     *
     * @param groupName Nazwa grupy
     */
    public StudentGroup(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Konstruktor z parametrami identyfikatora i nazwy grupy.
     *
     * @param groupId   Identyfikator grupy
     * @param groupName Nazwa grupy
     */
    public StudentGroup(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    /**
     * Zwraca identyfikator grupy.
     *
     * @return Identyfikator grupy
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Ustawia identyfikator grupy.
     *
     * @param groupId Identyfikator grupy
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Zwraca nazwę grupy.
     *
     * @return Nazwa grupy
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Ustawia nazwę grupy.
     *
     * @param groupName Nazwa grupy
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
