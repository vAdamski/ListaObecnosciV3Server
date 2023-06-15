package Shared.Helpers;

import Shared.Enums.PresenceStatus;

/**
 * Klasa pomocnicza do konwersji statusu obecności.
 */
public class PresenceStatusHelper {
    /**
     * Konwertuje status obecności na String.
     *
     * @param presenceStatus Status obecności.
     * @return Status obecności w postaci String.
     */
    public static String getPresenceStatus(PresenceStatus presenceStatus) {
        switch (presenceStatus) {
            case PRESENT:
                return "Present";
            case ABSENT:
                return "Absent";
            case JUSTIFIED:
                return "Justified";
            case LATE:
                return "Late";
            default:
                return "Unknown";
        }
    }

    /**
     * Konwertuje status obecności na PresenceStatus.
     *
     * @param presenceStatus Status obecności w postaci String.
     * @return Status obecności.
     */
    public static PresenceStatus getPresenceStatus(String presenceStatus) {
        switch (presenceStatus.toUpperCase()) {
            case "PRESENT":
                return PresenceStatus.PRESENT;
            case "ABSENT":
                return PresenceStatus.ABSENT;
            case "JUSTIFIED":
                return PresenceStatus.JUSTIFIED;
            case "LATE":
                return PresenceStatus.LATE;
            default:
                return PresenceStatus.UNKNOWN;
        }
    }
}
