package Repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa abstrakcyjna przechowująca podstawową implementację bazy danych.
 * Wykorzystuje obiekty EntityManager i EntityManagerFactory do zarządzania połączeniem i operacjami na bazie danych.
 */
public class BaseRepository {
    /**
     * Obiekt EntityManagerFactory - fabryka obiektów EntityManager.
     * Inicjalizowany na podstawie konfiguracji, określającej źródło danych i dostawcę JPA.
     */
    private EntityManagerFactory _entityManagerFactory;
    /**
     * Obiekt EntityManager - służy do zarządzania encjami (obiektami reprezentującymi dane) w JPA.
     * Wykorzystywany do operacji na bazie danych, zarządzania cyklem życia encji oraz wykonania zapytań i transakcji.
     */
    protected EntityManager _entityManager;

    /**
     * Konstruktor inicjalizujący obiekty EntityManagerFactory i EntityManager.
     * Ustawia właściwości persystencji, takie jak dialekt bazy danych i tryb wyświetlania zapytań SQL.
     */
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

