package Shared.ViewModels;

/**
 * Klasa reprezentująca dane potrzebne do pobrania listy obecności.
 */
public class GetPresentsListVm {
    /**
     * Identyfikator grupy.
     */
    private Integer groupId;

    /**
     * Identyfikator terminu.
     */
    private Integer periodId;

    /**
     * Konstruktor klasy GetPresentsListVm z parametrami groupId i periodId.
     *
     * @param groupId   Identyfikator grupy.
     * @param periodId Identyfikator terminu.
     */
    public GetPresentsListVm(Integer groupId, Integer periodId) {
        this.groupId = groupId;
        this.periodId = periodId;
    }

    /**
     * Konstruktor domyślny bezparametrowy klasy GetPresentsListVm.
     */
    public GetPresentsListVm() {
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
     * Zwraca identyfikator terminu.
     *
     * @return Identyfikator terminu.
     */
    public Integer getPeriodId() {
        return periodId;
    }

    /**
     * Ustawia identyfikator terminu.
     *
     * @param periodId Identyfikator terminu.
     */
    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }
}

