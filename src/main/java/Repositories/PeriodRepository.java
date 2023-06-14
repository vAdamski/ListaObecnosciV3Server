package Repositories;

import Shared.Entities.Period;

import java.util.ArrayList;

public class PeriodRepository extends BaseRepository{
    public PeriodRepository() {
        super();
    }

    public void createPeriod(Period period) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(period);
        _entityManager.getTransaction().commit();
    }

    public void deletePeriod(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Period p WHERE p.periodId = :id")
                .setParameter("id", id)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    public ArrayList<Period> getListOfPeriods() {
        ArrayList<Period> periods = (ArrayList<Period>) _entityManager.createQuery("SELECT p FROM Period p").getResultList();
        return periods;
    }

    public Period getPeriodById(int id) {
        Period period = (Period) _entityManager.createQuery("SELECT p FROM Period p WHERE p.periodId = :id")
                .setParameter("id", id)
                .getSingleResult();
        return period;
    }

    public ArrayList<Period> getListOfPeriodsForSubject(int subjectId) {
        ArrayList<Period> periods = (ArrayList<Period>) _entityManager.createQuery("SELECT p FROM Period p WHERE p.subjectId = :subjectId")
                .setParameter("subjectId", subjectId)
                .getResultList();
        return periods;
    }

    public ArrayList<Period> getListOfPeriodsForSubjectAndGroup(Integer subjectId, Integer groupId) {
        ArrayList<Period> periods = (ArrayList<Period>) _entityManager.createQuery("SELECT p FROM Period p WHERE p.subjectId = :subjectId AND p.groupId = :groupId")
                .setParameter("subjectId", subjectId)
                .setParameter("groupId", groupId)
                .getResultList();
        return periods;
    }

    public void deleteAllPeriodsForSubject(Integer object) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Period WHERE subjectId = :subjectId")
                .setParameter("subjectId", object)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }
}
