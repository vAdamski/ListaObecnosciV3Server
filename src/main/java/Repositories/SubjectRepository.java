package Repositories;

import Shared.Entities.Subject;

import java.util.ArrayList;

public class SubjectRepository extends BaseRepository {
    public SubjectRepository() {
        super();
    }

    public ArrayList<Subject> getListOfSubjects() {
        return (ArrayList<Subject>) _entityManager.createQuery("SELECT s FROM Subject s").getResultList();
    }

    public void createSubject(Subject subject) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(subject);
        _entityManager.getTransaction().commit();
        
    }

    public void deleteSubject(Subject subject) {
        _entityManager.getTransaction().begin();
        _entityManager.remove(subject);
        _entityManager.getTransaction().commit();
        
    }

    public void deleteSubject(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Subject s WHERE s.subjectId = :id")
                .setParameter("id", id)
                .executeUpdate();
        _entityManager.getTransaction().commit();
        
    }

    public void deleteSubject(String name) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Subject s WHERE s.name = :name")
                .setParameter("name", name)
                .executeUpdate();
        _entityManager.getTransaction().commit();
        
    }
}
