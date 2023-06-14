package BusinessLogic.Students.Commands.CreateStudent;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PeriodRepository;
import Repositories.PresenceRepository;
import Shared.Entities.Period;
import Shared.Entities.Presence;
import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za obsługę żądania tworzenia studenta
 * Implementuje interfejs IRequestHandler
 */
public class CreateStudentCommandHandler implements IRequestHandler {
    /**
     * Repozytorium studenta
     */
    private final StudentRepository _studentRepository;
    /**
     * Repozytorium obecności
     */
    private final PresenceRepository _presenceRepository;
    /**
     * Repozytorium terminów
     */
    private final PeriodRepository _periodRepository;

    /**
     * Konstruktor klasy CreateStudentCommandHandler
     * Tworzy obiekty klasy StudentRepository, PresenceRepository, PeriodRepository
     */
    public CreateStudentCommandHandler() {
        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
        _periodRepository = new PeriodRepository();
    }

    /**
     * Wykonuje logikę biznesową tworzenia studenta
     *
     * @param json DataHandler<Student> w formacie JSON gdzie Student to obiekt studenta.
     * @return ResponseHandler<Boolean> w formacie JSON
     * @throws Exception Rzucany, gdy nie udało się utworzyć studenta
     */
    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<Student>> typeReference = new TypeReference<DataHandler<Student>>() {
            };
            DataHandler<Student> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            // Jeśli group id jest puste to nie dodawaj obecności do wszystkich terminów
            // W przeciwnym przyapdku pobier wszystkie terminy dla danej grupy i dla każdego terminu dodaj obecność dla danego studenta
            if (dataHandler.getObject().getGroupId() != null) {
                ArrayList<Period> periods = _periodRepository.getListOfPeriodsForGroup(dataHandler.getObject().getGroupId());

                for (Period period : periods) {
                    _presenceRepository.createPresence(new Presence(dataHandler.getObject().getStudentIndex(), period.getPeriodId(), "UNKNOWN", null));
                }
            }

            _studentRepository.createStudent(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, errors, false));
        }
    }
}
