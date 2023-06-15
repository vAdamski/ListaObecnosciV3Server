package BusinessLogic.Periods.Commands.CreatePeriod;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PeriodRepository;
import Repositories.PresenceRepository;
import Repositories.StudentRepository;
import Shared.Entities.Period;
import Shared.Entities.Presence;
import Shared.Entities.Student;
import Shared.Enums.PresenceStatus;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą tworzenia terminu.
 * Implementuje interfejs IRequestHandler.
 */
public class CreatePeriodCommandHandler implements IRequestHandler {
    /**
     * Repozytorium terminów.
     */
    private final PeriodRepository _periodRepository;
    /**
     * Repozytorium studentów.
     */
    private final StudentRepository _studentRepository;
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;
    /**
     * Konstruktor klasy CreatePeriodCommandHandler.
     * Tworzy obiekty klasy PeriodRepository, StudentRepository, PresenceRepository.
     */
    public CreatePeriodCommandHandler() {
        _periodRepository = new PeriodRepository();
        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
    }

    /**
     * Wykonuje logikę biznesową tworzenia terminu.
     *
     * @param json String DataHandler<Period> w formacie JSON gdzie Period jest terminem.
     * @return String ResponseHandler<Boolean> w formacie JSON zawierający informację o powodzeniu operacji.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się utworzyć terminu.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<Period>> typeReference = new TypeReference<DataHandler<Period>>() {};
            DataHandler<Period> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _periodRepository.createPeriod(dataHandler.getObject());

            ArrayList<Student> studentsInGroup = _studentRepository.getStudentListForGroup(dataHandler.getObject().getGroupId());

            for (Student student : studentsInGroup) {
                _presenceRepository.createPresence(new Presence( student.getStudentIndex(), dataHandler.getObject().getPeriodId(), PresenceStatus.UNKNOWN.toString(), null));
            }

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(), false));
        }
    }
}
