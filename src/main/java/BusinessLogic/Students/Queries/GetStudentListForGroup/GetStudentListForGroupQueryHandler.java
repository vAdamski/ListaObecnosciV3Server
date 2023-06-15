package BusinessLogic.Students.Queries.GetStudentListForGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.StudentRepository;
import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Klasa reprezentująca logikę biznesową pobierania listy studentów dla grupy.
 * Implementuje interfejs IRequestHandler.
 */
public class GetStudentListForGroupQueryHandler implements IRequestHandler {
    /**
     * Repozytorium studentów.
     */
    private final StudentRepository _studentRepository;
    /**
     * Konstruktor klasy GetStudentListForGroupQueryHandler.
     */
    public GetStudentListForGroupQueryHandler() {
        _studentRepository = new StudentRepository();
    }

    /**
     * Metoda obsługująca żądanie pobrania listy studentów dla grupy.
     *
     * @param json String Json przedstawiający DataHandler<Integer> z id grupy.
     * @return String Json zawierający ResponseHandler<ArrayList<Student>> z listą studentów.
     * @throws Exception Wyjątek wyrzucany w przypadku niepowodzenia operacji.
     */
    @Override
    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            ArrayList<Student> students = _studentRepository.getStudentListForGroup(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Student>>(students, true));
        } catch (Exception e) {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Student>>(null, e.getMessage(), false));
        }
    }
}
