package BusinessLogic.Periods.Queries.GetAllPeriodsForSubject;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Period;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.PeriodRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class GetListOfPeriodsForSubjectQueryHandler implements IRequestHandler {
    private final PeriodRepository _periodRepository;
    public GetListOfPeriodsForSubjectQueryHandler() {
        _periodRepository = new PeriodRepository();
    }

    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            ArrayList<Period> periods = _periodRepository.getListOfPeriodsForSubject(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Period>>(periods, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Period>>(null, e.getMessage(), false));
        }
    }
}
