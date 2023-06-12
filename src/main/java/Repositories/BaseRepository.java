package Repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class BaseRepository {
    private EntityManagerFactory _entityManagerFactory;
    protected EntityManager _entityManager;
    BaseRepository() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("javax.persistence.packagesToScan", "Shared/Entities");


        _entityManagerFactory = Persistence.createEntityManagerFactory("default", properties);
        _entityManager = _entityManagerFactory.createEntityManager();
    }
}
