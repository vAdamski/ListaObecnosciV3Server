package Repositories;

import Shared.Entities.Period;

import java.util.ArrayList;


/**
 * Klasa `PeriodRepository` rozszerza `BaseRepository` i dostarcza metody do zarządzania terminami w bazie danych.
 * Wykorzystuje obiekty `EntityManager` i `EntityManagerFactory` odziedziczone z `BaseRepository`.
 */
public class PeriodRepository extends BaseRepository {

    /**
     * Konstruktor bezargumentowy inicjalizujący `PeriodRepository` i odwołujący się do konstruktora `BaseRepository`.
     */
    public PeriodRepository() {
        super();
    }

    /**
     * Tworzy nowy termin w bazie danych.
     *
     * @param period Termin do dodania.
     */
    public void createPeriod(Period period) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(period);
        _entityManager.getTransaction().commit();
    }

    /**
     * Usuwa termin z bazy danych na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator terminu do usunięcia.
     */
    public void deletePeriod(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Period p WHERE p.periodId = :id").setParameter("id", id).executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Pobiera listę terminów dla danego identyfikatora przedmiotu.
     *
     * @param subjectId Identyfikator przedmiotu.
     * @return Lista terminów dla danego przedmiotu.
     */
    public ArrayList<Period> getListOfPeriodsForSubject(int subjectId) {
        ArrayList<Period> periods = (ArrayList<Period>) _entityManager.createQuery("SELECT p FROM Period p WHERE p.subjectId = :subjectId").setParameter("subjectId", subjectId).getResultList();
        return periods;
    }

    /**
     * Pobiera listę terminów dla danego identyfikatora przedmiotu i grupy.
     *
     * @param subjectId Identyfikator przedmiotu.
     * @param groupId   Identyfikator grupy.
     * @return Lista terminów dla danego przedmiotu i grupy.
     */
    public ArrayList<Period> getListOfPeriodsForSubjectAndGroup(Integer subjectId, Integer groupId) {
        ArrayList<Period> periods = (ArrayList<Period>) _entityManager.createQuery("SELECT p FROM Period p WHERE p.subjectId = :subjectId AND p.groupId = :groupId").setParameter("subjectId", subjectId).setParameter("groupId", groupId).getResultList();
        return periods;
    }

    /**
     * Usuwa wszystkie terminy dla danego identyfikatora przedmiotu.
     *
     * @param subjectId Identyfikator przedmiotu.
     */
    public void deleteAllPeriodsForSubject(Integer subjectId) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Period WHERE subjectId = :subjectId").setParameter("subjectId", subjectId).executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Pobiera listę terminów dla danego identyfikatora grupy.
     *
     * @param groupId Identyfikator grupy.
     * @return Lista terminów dla danej grupy.
     */
    public ArrayList<Period> getListOfPeriodsForGroup(Integer groupId) {
        ArrayList<Period> periods = (ArrayList<Period>) _entityManager.createQuery("SELECT p FROM Period p WHERE p.groupId = :groupId").setParameter("groupId", groupId).getResultList();
        return periods;
    }
}