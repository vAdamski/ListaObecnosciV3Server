package BusinessLogic.Presences.Queries.GetPresenceList;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Repositories.StudentRepository;
import Shared.Entities.Presence;
import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.PresenceVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Przechowuje logikę biznesową dotyczącą pobierania listy obecności.
 * Implementuje interfejs IRequestHandler.
 */
public class GetPresenceListQueryHandler implements IRequestHandler {
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;

    /**
     * Repozytorium studentów.
     */
    private final StudentRepository _studentRepository;

    /**
     * Konstruktor klasy GetPresenceListQueryHandler.
     * Tworzy obiekty klasy PresenceRepository, StudentRepository.
     */
    public GetPresenceListQueryHandler() {
        _presenceRepository = new PresenceRepository();
        _studentRepository = new StudentRepository();
    }

    /**
     * Wykonuje logikę biznesową pobierania listy obecności.
     *
     * @param json String DataHandler<Integer> w formacie JSON gdzie Integer jest identyfikatorem terminu.
     * @return String ResponseHandler<ArrayList<PresenceVm>> w formacie JSON zawierający listę obecności.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się pobrać listy obecności.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            ArrayList<Presence> presences = _presenceRepository.getPresenceListForPeriod(dataHandler.getObject());
            ArrayList<Student> students = _studentRepository.getStudentsList();

            ArrayList<PresenceVm> presenceVms = new ArrayList<>();

            presences.forEach(presence -> {
                Student student = students.stream().filter(x -> Objects.equals(x.getStudentIndex(), presence.getStudentIndex())).findFirst().orElse(null);
                presenceVms.add(new PresenceVm(
                        student.getStudentIndex(),
                        presence.getPeriodId(),
                        presence.getStatus(),
                        student.getFirstName(),
                        student.getLastName()));
            });

            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<PresenceVm>>(presenceVms, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<PresenceVm>>(null, e.getMessage(), false));
        }
    }
}
