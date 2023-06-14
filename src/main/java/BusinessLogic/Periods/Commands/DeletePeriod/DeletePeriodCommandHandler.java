package BusinessLogic.Periods.Commands.DeletePeriod;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.PeriodRepository;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeletePeriodCommandHandler implements IRequestHandler {
    private final PeriodRepository _periodRepository;
    private final PresenceRepository _presenceRepository;
    public DeletePeriodCommandHandler() {
        _periodRepository = new PeriodRepository();
        _presenceRepository = new PresenceRepository();
    }

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
