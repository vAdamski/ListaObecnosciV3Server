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

public class GetListForGroupAndSubjectQueryHandler implements IRequestHandler {
    private final PeriodRepository _periodRepository;

    public GetListForGroupAndSubjectQueryHandler() {

        _periodRepository = new PeriodRepository();
    }

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
