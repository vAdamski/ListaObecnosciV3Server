package BusinessLogic.Periods.Commands.DeletePeriod;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.PeriodRepository;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Przechowuje logikę biznesową dotyczącą usuwania terminu.
 * Implementuje interfejs IRequestHandler.
 */
public class DeletePeriodCommandHandler implements IRequestHandler {
    /**
     * Repozytorium terminów.
     */
    private final PeriodRepository _periodRepository;
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;
    /**
     * Konstruktor klasy DeletePeriodCommandHandler.
     * Tworzy obiekty klasy PeriodRepository, PresenceRepository.
     */
    public DeletePeriodCommandHandler() {
        _periodRepository = new PeriodRepository();
        _presenceRepository = new PresenceRepository();
    }

    /**
     * Wykonuje logikę biznesową usuwania terminu.
     *
     * @param json String DataHandler<Integer> w formacie JSON gdzie Integer jest identyfikatorem terminu do usunięci.
     * @return String ResponseHandler<Boolean> w formacie JSON zawierający informację o powodzeniu operacji.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się usunąć terminu.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _periodRepository.deletePeriod(dataHandler.getObject());

            _presenceRepository.deletePresencesForPeriod(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(null, e.getMessage(), false));
        }
    }
}
