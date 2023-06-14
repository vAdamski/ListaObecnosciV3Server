package BusinessLogic.Students.Commands.AssigneStudentToGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PeriodRepository;
import Repositories.PresenceRepository;
import Repositories.StudentRepository;
import Shared.Entities.Period;
import Shared.Entities.Presence;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.StudentToGroupVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class AssigneStudentToGroupCommandHandler implements IRequestHandler {
    private final StudentRepository _studentRepository;
    private final PresenceRepository _presenceRepository;
    private final PeriodRepository _periodRepository;

    public AssigneStudentToGroupCommandHandler() {

        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
        _periodRepository = new PeriodRepository();
    }

    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<StudentToGroupVm>> typeReference = new TypeReference<DataHandler<StudentToGroupVm>>() {};
            DataHandler<StudentToGroupVm> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _studentRepository.assigneStudentToGroup(dataHandler.getObject().getStudentIndex(), dataHandler.getObject().getGroupId());

            ArrayList<Period> periods = _periodRepository.getListOfPeriodsForGroup(dataHandler.getObject().getGroupId());

            for (Period period : periods) {
                _presenceRepository.createPresence(new Presence( dataHandler.getObject().getStudentIndex(), period.getPeriodId(), "UNKNOWN", null));
            }

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(),false));
        }
    }
}
