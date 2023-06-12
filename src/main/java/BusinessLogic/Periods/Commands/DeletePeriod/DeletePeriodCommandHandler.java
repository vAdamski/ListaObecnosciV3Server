package BusinessLogic.Periods.Commands.DeletePeriod;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.PeriodRepository;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeletePeriodCommandHandler implements IRequestHandler {
    private final PeriodRepository _periodRepository;
    public DeletePeriodCommandHandler() {

        _periodRepository = new PeriodRepository();
    }

    @Override
    public ResponseHandler<?> handle(String json) {
        try
        {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _periodRepository.deletePeriod(dataHandler.getObject());

            return new ResponseHandler<Boolean>(true, true);
        }
        catch (Exception e)
        {
            return new ResponseHandler<Boolean>(null, e.getMessage(), false);
        }
    }
}
