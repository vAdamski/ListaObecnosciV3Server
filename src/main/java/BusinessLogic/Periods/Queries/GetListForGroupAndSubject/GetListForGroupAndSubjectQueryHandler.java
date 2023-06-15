package BusinessLogic.Periods.Queries.GetListForGroupAndSubject;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PeriodRepository;
import Shared.Entities.Period;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.GetPeriodsListVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą pobierania terminów dla przedmiotu.
 * Implementuje interfejs IRequestHandler.
 */
public class GetListForGroupAndSubjectQueryHandler implements IRequestHandler {
    /**
     * Repozytorium terminów.
     */
    private final PeriodRepository _periodRepository;

    /**
     * Konstruktor klasy GetListOfPeriodsForSubjectQueryHandler.
     * Tworzy obiekt klasy PeriodRepository.
     */
    public GetListForGroupAndSubjectQueryHandler() {

        _periodRepository = new PeriodRepository();
    }

    /**
     * Wykonuje logikę biznesową pobierania terminów dla przedmiotu.
     *
     * @param json String DataHandler<Integer> w formacie JSON gdzie GetPeriodsListVm jest obiektem zawierającym id przedmiotu i grupy.
     * @return String ResponseHandler<ArrayList<Period>> w formacie JSON zawierający listę terminów.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się pobrać terminów.
     */
    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<GetPeriodsListVm>> typeReference = new TypeReference<DataHandler<GetPeriodsListVm>>() {};
            DataHandler<GetPeriodsListVm> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            ArrayList<Period> periods = _periodRepository.getListOfPeriodsForSubjectAndGroup(dataHandler.getObject().getSubjectId(), dataHandler.getObject().getGroupId());

            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Period>>(periods, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Period>>(null, e.getMessage(), false));
        }
    }
}
