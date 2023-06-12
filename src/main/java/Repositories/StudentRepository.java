package Repositories;

import Shared.Entities.Student;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;

public class StudentRepository extends BaseRepository {
    public StudentRepository() {
        super();
    }

    public void createStudent(Student student) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(student);
        _entityManager.getTransaction().commit();
        
    }

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
//        
    }

    public void deleteStudent(Student student) {
        _entityManager.getTransaction().begin();
        _entityManager.remove(student);
        _entityManager.getTransaction().commit();
        
    }

    public void deleteStudent(String index) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Student s WHERE s.studentIndex = :index")
                .setParameter("index", index)
                .executeUpdate();
        _entityManager.getTransaction().commit();
        
    }

    public Student getStudentById(int id) {
        Student student = _entityManager.find(Student.class, id);
        
        return student;
    }

    public Student getStudentByIndex(String index) {
        Student student = _entityManager.createQuery("SELECT s FROM Student s WHERE s.studentIndex = :index", Student.class)
                .setParameter("index", index)
                .getSingleResult();
        
        return student;
    }

    public ArrayList<Student> getStudentsList() {
        ArrayList<Student> students = (ArrayList<Student>) _entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        return students;
    }

    public ArrayList<Student> getStudentListForGroup(int groupId)
    {
        ArrayList<Student> students = (ArrayList<Student>) _entityManager.createQuery("SELECT s FROM Student s WHERE s.groupId = :groupId", Student.class)
                .setParameter("groupId", groupId)
                .getResultList();

        return students;
    }
}
