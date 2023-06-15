package BusinessLogic.Subjects.Commands.DeleteSubject;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PeriodRepository;
import Repositories.PresenceRepository;
import Shared.Entities.Period;
import Shared.Entities.Presence;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą usuwania przedmiotu.
 * Implementuje interfejs IRequestHandler.
 */
public class DeleteSubjectCommandHandler implements IRequestHandler {
    /**
     * Repozytorium przedmiotu.
     */
    private final SubjectRepository _subjectRepository;
    /**
     * Repozytorium terminów.
     */
    private final PeriodRepository _periodRepository;
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;
    /**
     * Konstruktor klasy DeleteSubjectCommandHandler.
     * Tworzy obiekty klasy SubjectRepository, PeriodRepository, PresenceRepository.
     */
    public DeleteSubjectCommandHandler() {
        _subjectRepository = new SubjectRepository();
        _periodRepository = new PeriodRepository();
        _presenceRepository = new PresenceRepository();
    }

    /**
     * Wykonuje logikę biznesową usuwania przedmiotu.
     *
     * @param json DataHandler<Integer> w formacie JSON gdzie Integer jest id przedmiotu.
     * @return ResponseHandler<Boolean> w formacie JSON zawierający informację o powodzeniu operacji.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się usunąć przedmiotu.
     */
    @Override
    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            ArrayList<Period> periodsForSubject = _periodRepository.getListOfPeriodsForSubject(dataHandler.getObject());
            ArrayList<Presence> presences = new ArrayList<>();

            periodsForSubject.forEach(period -> {
                _presenceRepository.deleteAllPresencesForPeriod(period.getPeriodId());
            });

            _periodRepository.deleteAllPeriodsForSubject(dataHandler.getObject());

            _subjectRepository.deleteSubject(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        } catch (Exception e) {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(), false));
        }
    }
}
