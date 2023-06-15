package Shared.Helpers.ResponseHandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Klasa reprezentująca odpowiedź serwera do klienta.
 *
 * @param <T> Typ danych.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHandler<T> {
    /**
     * Dane przesłane przez serwer.
     */
    private T _data;
    /**
     * Lista błędów.
     */
    private ArrayList<String> _errors;
    /**
     * Czy operacja się powiodła.
     */
    private boolean _isSuccess;

    /**
     * Konstruktor domyślny bezparametrowy.
     */
    public ResponseHandler() {
    }

    /**
     * Konstruktor z parametrami danymi, listą błędów i informacją o powodzeniu operacji.
     *
     * @param data      Dane
     * @param errors    Lista błędów
     * @param isSuccess Czy operacja się powiodła
     */
    public ResponseHandler(T data, ArrayList<String> errors, boolean isSuccess) {
        _data = data;
        _errors = errors;
        _isSuccess = isSuccess;
    }

    /**
     * Konstruktor z parametrami danymi i informacją o powodzeniu operacji.
     *
     * @param data      Dane
     * @param error     Błąd
     * @param isSuccess Czy operacja się powiodła
     */
    public ResponseHandler(T data, String error, boolean isSuccess) {
        _data = data;
        _errors = new ArrayList<String>();
        _errors.add(error);
        _isSuccess = isSuccess;
    }

    /**
     * Konstruktor z parametrami danymi i informacją o powodzeniu operacji.
     *
     * @param data      Dane
     * @param isSuccess Czy operacja się powiodła
     */
    public ResponseHandler(T data, boolean isSuccess) {
        _data = data;
        _errors = new ArrayList<String>();
        _isSuccess = isSuccess;
    }

    /**
     * Sprawdza, czy operacja zakończyła się powodzeniem.
     *
     * @return True, jeśli operacja się powiodła. False w przeciwnym razie.
     */
    public boolean isSuccess() {
        return _isSuccess;
    }

    /**
     * Ustawia informację o powodzeniu operacji.
     *
     * @param success True, jeśli operacja się powiodła. False w przeciwnym razie.
     */
    public void setSuccess(boolean success) {
        this._isSuccess = success;
    }

    /**
     * Zwraca dane przesłane przez serwer.
     *
     * @return Dane przesłane przez serwer.
     */
    public T getData() {
        return _data;
    }

    /**
     * Ustawia dane przesłane przez serwer.
     *
     * @param data Dane przesłane przez serwer.
     */
    public void setData(T data) {
        this._data = data;
    }

    /**
     * Zwraca listę błędów.
     *
     * @return Lista błędów.
     */
    public ArrayList<String> getErrors() {
        return _errors;
    }

    /**
     * Ustawia listę błędów.
     *
     * @param errors Lista błędów.
     */
    public void setErrors(ArrayList<String> errors) {
        this._errors = errors;
    }
}
