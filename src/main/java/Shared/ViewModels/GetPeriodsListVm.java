package Shared.ViewModels;


/*
    * Klasa reprezentująca dane potrzebne do pobrania listy terminów.
 */
public class GetPeriodsListVm {
    /**
     * Identyfikator grupy.
     */
    private Integer groupId;

    /**
     * Identyfikator przedmiotu.
     */
    private Integer subjectId;

    /**
     * Konstruktor klasy GetPeriodsListVm z parametrami groupId i subjectId.
     *
     * @param groupId   Identyfikator grupy.
     * @param subjectId Identyfikator przedmiotu.
     */
    public GetPeriodsListVm(Integer groupId, Integer subjectId) {
        this.groupId = groupId;
        this.subjectId = subjectId;
    }

    /**
     * Konstruktor domyślny bezparametrowy klasy GetPeriodsListVm.
     */
    public GetPeriodsListVm() {
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
     * Ustawia identyfikator grupy.
     *
     * @param groupId Identyfikator grupy.
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Zwraca identyfikator przedmiotu.
     *
     * @return Identyfikator przedmiotu.
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Ustawia identyfikator przedmiotu.
     *
     * @param subjectId Identyfikator przedmiotu.
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}

