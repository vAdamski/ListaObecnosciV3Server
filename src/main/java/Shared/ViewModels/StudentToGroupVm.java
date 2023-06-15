package Shared.ViewModels;

/*
    * Klasa przechowująca dane potrzebne do przypisania lub usunięcia studenta z grupy.
 */
public class StudentToGroupVm {
    /**
     * Identyfikator grupy.
     */
    private Integer groupId;

    /**
     * Indeks studenta.
     */
    private String studentIndex;

    /**
     * Konstruktor klasy StudentToGroupVm z parametrami groupId i studentIndex.
     *
     * @param groupId     Identyfikator grupy.
     * @param studentIndex Indeks studenta.
     */
    public StudentToGroupVm(Integer groupId, String studentIndex) {
        this.groupId = groupId;
        this.studentIndex = studentIndex;
    }

    /**
     * Konstruktor domyślny bezparametrowy klasy StudentToGroupVm.
     */
    public StudentToGroupVm() {

    }

    /**
     * Ustawia identyfikator grupy.
     *
     * @param groupId Identyfikator grupy.
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Ustawia indeks studenta.
     *
     * @param studentIndex Indeks studenta.
     */
    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    /**
     * Zwraca identyfikator grupy.
     *
     * @return Identyfikator grupy.
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Zwraca indeks studenta.
     *
     * @return Indeks studenta.
     */
    public String getStudentIndex() {
        return studentIndex;
    }
}

