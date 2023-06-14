package BusinessLogic.Students.Commands.DeleteStudent;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą usuwania studenta.
 * Implementuje interfejs IRequestHandler.
 */
public class DeleteStudentCommandHandler implements IRequestHandler {
    /**
     * Repozytorium studenta.
     */
    private final StudentRepository _studentRepository;
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;

    /**
     * Konstruktor klasy DeleteStudentCommandHandler.
     * Tworzy obiekty klasy StudentRepository, PresenceRepository.
     */
    public DeleteStudentCommandHandler() {
        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
    }

    /**
     * Wykonuje logikę biznesową usuwania studenta.
     *
     * @param json DataHandler<String> w formacie JSON gdzie string jest indeksem studenta.
     * @return ResponseHandler<Boolean> w formacie JSON.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się usunąć studenta.
     */
    @Override
    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<String>> typeReference = new TypeReference<DataHandler<String>>() {
            };
            DataHandler<String> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _presenceRepository.deleteAllPresencesForStudent(dataHandler.getObject());

            _studentRepository.deleteStudent(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, errors, false));
        }
    }
}
