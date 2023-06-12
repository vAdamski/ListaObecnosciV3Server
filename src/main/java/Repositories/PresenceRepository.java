package Repositories;

import Shared.Entities.Presence;

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
        _entityManager.getTransaction().begin();
        _entityManager.remove(presence);
        _entityManager.getTransaction().commit();
        
    }

    public void deletePresence(int id) {
        _entityManager.getTransaction().begin();
        _entityManager.createQuery("DELETE FROM Presence p WHERE p.periodId = :id")
                .setParameter("id", id)
                .executeUpdate();
        _entityManager.getTransaction().commit();
        
    }
}
