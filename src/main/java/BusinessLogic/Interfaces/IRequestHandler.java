package BusinessLogic.Interfaces;

import Shared.Helpers.ResponseHandler.ResponseHandler;
/**
 * IRequestHandler interface
 * Ten interface jest implementowany przez klasy, które obsługują zapytania od klientów
 */
public interface IRequestHandler {
    /**
     * Metoda handle obsługuje zapytanie od klienta. Przyjmuje klasę przekonwertowaną na JSONa,
     * a odpowiedź zwraca w postaci JSONa
     * @param json String
     * @return String
     * @throws Exception
     */
    String handle(String json) throws Exception;
}
