package BusinessLogic.Presences.Commands.UpdatePresence;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.PresenceVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą aktulaizacji listy obecności.
 * Implementuje interfejs IRequestHandler.
 */
public class UpdatePresenceCommandHandler implements IRequestHandler {
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;

    /**
     * Konstruktor klasy UpdatePresenceCommandHandler.
     * Tworzy obiekt klasy PresenceRepository.
     */
    public UpdatePresenceCommandHandler() {
        _presenceRepository = new PresenceRepository();
    }

    /**
     * Wykonuje logikę biznesową.
     * @param json Dane wejściowe w formacie JSON gdzie ArrayList<PresenceVm> jest listą obecności.
     * @return Dane wyjściowe w formacie JSON zawierające informację o powodzeniu operacji.
     * @throws Exception
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<ArrayList<PresenceVm>>> typeReference = new TypeReference<DataHandler<ArrayList<PresenceVm>>>() {};
            DataHandler<ArrayList<PresenceVm>> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            ArrayList<PresenceVm> presences = dataHandler.getObject();

            presences.forEach(presence -> {
                _presenceRepository.updateStudentPresence(presence.getPeriodId(), presence.getStudentIndex(), presence.getStatus());
            });

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(), false));
        }
    }
}
