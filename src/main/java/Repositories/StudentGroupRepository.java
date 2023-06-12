package Repositories;

import Shared.Entities.StudentGroup;

import java.util.ArrayList;

public class StudentGroupRepository extends BaseRepository {
    public StudentGroupRepository() {
        super();
    }

    public void createStudentGroup(StudentGroup studentGroup) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(studentGroup);
        _entityManager.getTransaction().commit();
        
    }

    public void deleteStudentGroup(StudentGroup studentGroup) {
        _entityManager.getTransaction().begin();
        _entityManager.remove(studentGroup);
        _entityManager.getTransaction().commit();
        
    }

    public void deleteStudentGroup(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM StudentGroup sg WHERE sg.groupId = :id")
                .setParameter("id", id)
                .executeUpdate();
        _entityManager.getTransaction().commit();
        
    }

    public void deleteStudentGroup(String name) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM StudentGroup sg WHERE sg.groupName = :name")
                .setParameter("name", name)
                .executeUpdate();
        _entityManager.getTransaction().commit();
        
    }

    public ArrayList<StudentGroup> getListOfGroups() {
        ArrayList<StudentGroup> studentGroups = (ArrayList<StudentGroup>) _entityManager.createQuery("SELECT sg FROM StudentGroup sg").getResultList();
        
        return studentGroups;
    }
}
