package BusinessLogic.Students.Queries.GetStudentsWithoutGroupList;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.StudentRepository;
import Shared.Entities.Student;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Przechowuje logikę biznesową dotyczącą pobierania listy studentów bez grupy.
 * Implementuje interfejs IRequestHandler.
 */
public class GetStudentsWithoutGroupListQueryHandler implements IRequestHandler {
    /**
     * Repozytorium studenta.
     */
    private final StudentRepository _studentRepository;
    /**
     * Konstruktor klasy GetStudentsWithoutGroupListQueryHandler.
     * Tworzy obiekt klasy StudentRepository.
     */
    public GetStudentsWithoutGroupListQueryHandler() {
        _studentRepository = new StudentRepository();
    }

    /**
     * Wykonuje logikę biznesową pobierania listy studentów bez grupy.
     *
     * @param json DataHandler<String> w formacie JSON gdzie string może być pusty ponieważ nie ma wpływu na wynik.
     * @return ResponseHandler<ArrayList < Student>> w formacie JSON gdzie ArrayList<Student> jest listą studentów.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się pobrać listy studentów.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            ArrayList<Student> students = _studentRepository.getStudentsList().stream().filter(x -> x.getGroupId() == null).collect(Collectors.toCollection(ArrayList::new));

            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Student>>(students, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Student>>(null, e.getMessage(), false));
        }
    }
}
