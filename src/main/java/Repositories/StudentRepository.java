package Repositories;

import Shared.Entities.Student;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;

/**
 * Klasa `StudentRepository` rozszerza `BaseRepository` i dostarcza metody do zarządzania studentami w bazie danych.
 * Wykorzystuje obiekty `EntityManager` i `EntityManagerFactory` odziedziczone z `BaseRepository`.
 */
public class StudentRepository extends BaseRepository {

    /**
     * Konstruktor bezargumentowy inicjalizujący `StudentRepository` i odwołujący się do konstruktora `BaseRepository`.
     */
    public StudentRepository() {
        super();
    }

    /**
     * Tworzy nowego studenta w bazie danych.
     *
     * @param student Student do dodania.
     */
    public void createStudent(Student student) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(student);
        _entityManager.getTransaction().commit();
    }

    /**
     * Aktualizuje dane studenta w bazie danych.
     *
     * @param student Student do zaktualizowania.
     */
    public void updateStudent(Student student) {
        EntityTransaction transaction = _entityManager.getTransaction();
        transaction.begin();

        _entityManager.createQuery("UPDATE Student s SET s.firstName = :firstName, s.lastName = :lastName, s.groupId = :groupId WHERE s.studentIndex = :studentIndex")
                .setParameter("firstName", student.getFirstName())
                .setParameter("lastName", student.getLastName())
                .setParameter("studentIndex", student.getStudentIndex())
                .setParameter("groupId", student.getGroupId())
                .executeUpdate();

        transaction.commit();
    }

    /**
     * Usuwa studenta z bazy danych na podstawie podanego indeksu.
     *
     * @param index Indeks studenta do usunięcia.
     */
    public void deleteStudent(String index) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Student s WHERE s.studentIndex = :index")
                .setParameter("index", index)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Pobiera listę wszystkich studentów z bazy danych.
     *
     * @return Lista studentów.
     */
    public ArrayList<Student> getStudentsList() {
        ArrayList<Student> students = (ArrayList<Student>) _entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        return students;
    }

    /**
     * Pobiera listę studentów dla danej grupy na podstawie identyfikatora grupy.
     *
     * @param groupId Identyfikator grupy.
     * @return Lista studentów należących do danej grupy.
     */
    public ArrayList<Student> getStudentListForGroup(int groupId) {
        ArrayList<Student> students = (ArrayList<Student>) _entityManager.createQuery("SELECT s FROM Student s WHERE s.groupId = :groupId", Student.class)
                .setParameter("groupId", groupId)
                .getResultList();

        return students;
    }

    /**
     * Przypisuje studenta do danej grupy na podstawie indeksu studenta i identyfikatora grupy.
     *
     * @param studentIndex Indeks studenta.
     * @param groupId      Identyfikator grupy.
     */
    public void assigneStudentToGroup(String studentIndex, Integer groupId) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("UPDATE Student s SET s.groupId = :groupId WHERE s.studentIndex = :studentIndex")
                .setParameter("groupId", groupId)
                .setParameter("studentIndex", studentIndex)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    /**
     * Usuwa przypisanie studenta do danej grupy na podstawie indeksu studenta i identyfikatora grupy.
     *
     * @param studentIndex Indeks studenta.
     * @param groupId      Identyfikator grupy.
     */
    public void deleteStudentFromGroup(String studentIndex, Integer groupId) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("UPDATE Student s SET s.groupId = null WHERE s.studentIndex = :studentIndex AND s.groupId = :groupId")
                .setParameter("groupId", groupId)
                .setParameter("studentIndex", studentIndex)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }
}
