package Repositories;

import Shared.Entities.Presence;

import java.util.ArrayList;

public class PresenceRepository extends BaseRepository {
    public PresenceRepository() {
        super();
    }

    public void createPresence(Presence presence) {
        _entityManager.getTransaction().begin();
        _entityManager.persist(presence);
        _entityManager.getTransaction().commit();
    }

    public void deletePresence(Presence presence) {
        _entityManager.remove(presence);
    }

    public void deletePresence(int id) {
        _entityManager.createQuery("DELETE FROM Presence p WHERE p.periodId = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void deletePresencesForPeriod(Integer presenceId) {
        _entityManager.createQuery("DELETE FROM Presence p WHERE p.periodId = :presenceId")
                .setParameter("presenceId", presenceId)
                .executeUpdate();
    }

    public ArrayList<Presence> getPresenceListForPeriod(Integer periodId) {
        ArrayList<Presence> presences = (ArrayList<Presence>) _entityManager.createQuery("SELECT p FROM Presence p WHERE p.periodId = :periodId")
                .setParameter("periodId", periodId)
                .getResultList();
        return presences;
    }

    public void deleteAllPresencesForPeriod(Integer periodId) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Presence WHERE periodId = :periodId")
                .setParameter("periodId", periodId)
                .executeUpdate();
        _entityManager.getTransaction().commit();
    }

    public void updateStudentPresence(int periodId, String studentIndex, String status) {
        _entityManager.createQuery("UPDATE Presence p SET p.status = :status WHERE p.periodId = :periodId AND p.studentIndex = :studentIndex")
                .setParameter("status", status)
                .setParameter("periodId", periodId)
                .setParameter("studentIndex", studentIndex)
                .executeUpdate();
    }
}
