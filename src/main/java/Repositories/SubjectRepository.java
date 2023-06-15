package Repositories;

import Shared.Entities.Subject;

import java.util.ArrayList;

/**
 * Klasa `SubjectRepository` rozszerza `BaseRepository` i dostarcza metody do zarządzania przedmiotami w bazie danych.
 * Wykorzystuje obiekty `EntityManager` i `EntityManagerFactory` odziedziczone z `BaseRepository`.
 */
public class SubjectRepository extends BaseRepository {

    /**
     * Konstruktor bezargumentowy inicjalizujący `SubjectRepository` i odwołujący się do konstruktora `BaseRepository`.
     */
    public SubjectRepository() {
        super();
    }

    /**
     * Pobiera listę wszystkich przedmiotów z bazy danych.
     *
     * @return Lista przedmiotów.
     */
    public ArrayList<Subject> getListOfSubjects() {
        return (ArrayList<Subject>) _entityManager.createQuery("SELECT s FROM Subject s").getResultList();
    }

    /**
     * Tworzy nowy przedmiot w bazie danych.
     *
     * @param subject Przedmiot do dodania.
     */
    public void createSubject(Subject subject) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(subject);
        _entityManager.getTransaction().commit();
    }

    /**
     * Usuwa przedmiot z bazy danych na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator przedmiotu do usunięcia.
     */
    public void deleteSubject(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Subject WHERE subjectId = :id")
                .setParameter("id", id)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }
}

