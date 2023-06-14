package BusinessLogic.Presences.Commands.UpdatePresence;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.PresenceVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class UpdatePresenceCommandHandler implements IRequestHandler {
    private final PresenceRepository _presenceRepository;
    public UpdatePresenceCommandHandler() {
        _presenceRepository = new PresenceRepository();
    }

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
