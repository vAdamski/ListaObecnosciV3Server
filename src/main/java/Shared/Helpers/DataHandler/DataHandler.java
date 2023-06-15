package Shared.Helpers.DataHandler;

/**
 * Klasa reprezentująca dane przesyłane do serwera.
 *
 * @param <T> Typ danych
 */
public class DataHandler<T> {
    /**
     * Nazwa akcji do wykonania na serwerze.
     */
    private String actionName;
    /**
     * Dane do przesłania.
     */
    private T object;

    /**
     * Konstruktor domyślny bezparametrowy.
     */
    public DataHandler() {
    }

    /**
     * Konstruktor z parametrami nazwy akcji i danych.
     *
     * @param actionName Nazwa akcji
     * @param object     Dane
     */
    public DataHandler(String actionName, T object) {
        this.actionName = actionName;
        this.object = object;
    }

    /**
     * Zwraca nazwę akcji.
     *
     * @return Nazwa akcji
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Zwraca dane.
     *
     * @return Dane
     */
    public T getObject() {
        return object;
    }
}

