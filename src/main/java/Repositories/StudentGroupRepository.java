package Repositories;

import Shared.Entities.StudentGroup;

import java.util.ArrayList;

/**
 * Klasa `StudentGroupRepository` rozszerza `BaseRepository` i dostarcza metody do zarządzania grupami studentów w bazie danych.
 * Wykorzystuje obiekty `EntityManager` i `EntityManagerFactory` odziedziczone z `BaseRepository`.
 */
public class StudentGroupRepository extends BaseRepository {

    /**
     * Konstruktor bezargumentowy inicjalizujący `StudentGroupRepository` i odwołujący się do konstruktora `BaseRepository`.
     */
    public StudentGroupRepository() {
        super();
    }

    /**
     * Tworzy nową grupę studentów w bazie danych.
     *
     * @param studentGroup Grupa studentów do dodania.
     */
    public void createStudentGroup(StudentGroup studentGroup) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(studentGroup);
        _entityManager.getTransaction().commit();
    }

    /**
     * Usuwa grupę studentów z bazy danych na podstawie podanego identyfikatora.
     *
     * @param id Identyfikator grupy studentów do usunięcia.
     */
    public void deleteStudentGroup(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM StudentGroup sg WHERE sg.groupId = :id")
                .setParameter("id", id)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Pobiera listę wszystkich grup studentów z bazy danych.
     *
     * @return Lista grup studentów.
     */
    public ArrayList<StudentGroup> getListOfGroups() {
        ArrayList<StudentGroup> studentGroups = (ArrayList<StudentGroup>) _entityManager.createQuery("SELECT sg FROM StudentGroup sg").getResultList();
        return studentGroups;
    }
}

