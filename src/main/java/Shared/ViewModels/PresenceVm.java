package Shared.ViewModels;

/*
    * Klasa reprezentująca dane obecności studenta.
 */
public class PresenceVm {
    /**
     * Indeks studenta.
     */
    private String studentIndex;

    /**
     * Identyfikator terminu.
     */
    private int periodId;

    /**
     * Status obecności.
     */
    private String status;

    /**
     * Imię studenta.
     */
    private String firstName;

    /**
     * Nazwisko studenta.
     */
    private String lastName;

    /**
     * Konstruktor klasy PresenceVm z parametrami studentIndex, periodId, status, firstName i lastName.
     *
     * @param studentIndex Indeks studenta.
     * @param periodId Identyfikator terminu.
     * @param status Status obecności.
     * @param firstName Imię studenta.
     * @param lastName Nazwisko studenta.
     */
    public PresenceVm(String studentIndex, int periodId, String status, String firstName, String lastName) {
        this.studentIndex = studentIndex;
        this.periodId = periodId;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Konstruktor domyślny bezparametrowy klasy PresenceVm.
     */
    public PresenceVm() {

    }

    /**
     * Zwraca indeks studenta.
     *
     * @return Indeks studenta.
     */
    public String getStudentIndex() {
        return studentIndex;
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
     * Zwraca identyfikator terminu.
     *
     * @return Identyfikator terminu.
     */
    public int getPeriodId() {
        return periodId;
    }

    /**
     * Ustawia identyfikator terminu.
     *
     * @param periodId Identyfikator terminu.
     */
    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    /**
     * Zwraca status obecności.
     *
     * @return Status obecności.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Ustawia status obecności.
     *
     * @param status Status obecności.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Zwraca imię studenta.
     *
     * @return Imię studenta.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Ustawia imię studenta.
     *
     * @param firstName Imię studenta.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Zwraca nazwisko studenta.
     *
     * @return Nazwisko studenta.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Ustawia nazwisko studenta.
     *
     * @param lastName Nazwisko studenta.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
