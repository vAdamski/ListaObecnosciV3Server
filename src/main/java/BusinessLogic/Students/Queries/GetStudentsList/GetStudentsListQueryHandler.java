package BusinessLogic.Students.Queries.GetStudentsList;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Student;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentRepository;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą pobierania listy studentów.
 * Implementuje interfejs IRequestHandler.
 */
public class GetStudentsListQueryHandler implements IRequestHandler {
    /**
     * Repozytorium studenta.
     */
    private StudentRepository _studentRepository;

    /**
     * Konstruktor klasy GetStudentsListQueryHandler.
     * Tworzy obiekt klasy StudentRepository.
     */
    public GetStudentsListQueryHandler() {
        _studentRepository = new StudentRepository();
    }

    /**
     * Wykonuje logikę biznesową pobierania listy studentów.
     *
     * @param json DataHandler<String> w formacie JSON gdzie string może być pusty ponieważ nie ma wpływu na wynik.
     * @return ResponseHandler<ArrayList < Student>> w formacie JSON gdzie ArrayList<Student> jest listą studentów.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się pobrać listy studentów.
     */
    public String handle(String json) throws Exception {
        try {
            ArrayList<Student> students = _studentRepository.getStudentsList();
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Student>>(students, new ArrayList<>(), true));
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Student>>(null, errors, false));
        }
    }
}
