package Repositories;

import Shared.Entities.Presence;

import java.util.ArrayList;

/**
 * Klasa `PresenceRepository` rozszerza `BaseRepository` i dostarcza metody do zarządzania obecnościami w bazie danych.
 * Wykorzystuje obiekty `EntityManager` i `EntityManagerFactory` odziedziczone z `BaseRepository`.
 */
public class PresenceRepository extends BaseRepository {

    /**
     * Konstruktor bezargumentowy inicjalizujący `PresenceRepository` i odwołujący się do konstruktora `BaseRepository`.
     */
    public PresenceRepository() {
        super();
    }

    /**
     * Tworzy nową obecność w bazie danych.
     *
     * @param presence Obecność do dodania.
     */
    public void createPresence(Presence presence) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(presence);
        _entityManager.getTransaction().commit();
    }

    /**
     * Usuwa wszystkie obecności dla danego identyfikatora terminu.
     *
     * @param presenceId Identyfikator terminu.
     */
    public void deletePresencesForPeriod(Integer presenceId) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Presence p WHERE p.periodId = :presenceId")
                .setParameter("presenceId", presenceId)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Pobiera listę obecności dla danego identyfikatora terminu.
     *
     * @param periodId Identyfikator terminu.
     * @return Lista obecności dla danego terminu.
     */
    public ArrayList<Presence> getPresenceListForPeriod(Integer periodId) {
        ArrayList<Presence> presences = (ArrayList<Presence>) _entityManager.createQuery("SELECT p FROM Presence p WHERE p.periodId = :periodId")
                .setParameter("periodId", periodId)
                .getResultList();
        return presences;
    }

    /**
     * Usuwa wszystkie obecności dla danego identyfikatora terminu.
     *
     * @param periodId Identyfikator terminu.
     */
    public void deleteAllPresencesForPeriod(Integer periodId) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Presence WHERE periodId = :periodId")
                .setParameter("periodId", periodId)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Aktualizuje status obecności dla danego identyfikatora terminu i indeksu studenta.
     *
     * @param periodId     Identyfikator terminu.
     * @param studentIndex Indeks studenta.
     * @param status       Nowy status obecności.
     */
    public void updateStudentPresence(int periodId, String studentIndex, String status) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("UPDATE Presence p SET p.status = :status WHERE p.periodId = :periodId AND p.studentIndex = :studentIndex")
                .setParameter("status", status)
                .setParameter("periodId", periodId)
                .setParameter("studentIndex", studentIndex)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Usuwa wszystkie obecności dla danego indeksu studenta.
     *
     * @param studentIndex Indeks studenta.
     */
    public void deleteAllPresencesForStudent(String studentIndex) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Presence WHERE studentIndex = :studentIndex")
                .setParameter("studentIndex", studentIndex)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }
}

