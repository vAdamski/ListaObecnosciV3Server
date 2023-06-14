package BusinessLogic.Periods.Commands.CreatePeriod;

import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Mappers.CreatePeriodVmToPeriod;
import Repositories.PresenceRepository;
import Repositories.StudentRepository;
import Shared.Entities.Period;
import Shared.Entities.Presence;
import Shared.Entities.Student;
import Shared.Enums.PresenceStatus;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.PeriodRepository;
import Shared.ViewModels.CreatePeriodVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class CreatePeriodCommandHandler implements IRequestHandler {
    private final PeriodRepository _periodRepository;
    private final StudentRepository _studentRepository;
    private final PresenceRepository _presenceRepository;
    public CreatePeriodCommandHandler() {
        _periodRepository = new PeriodRepository();
        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
    }

    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<Period>> typeReference = new TypeReference<DataHandler<Period>>() {};
            DataHandler<Period> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _periodRepository.createPeriod(dataHandler.getObject());

            ArrayList<Student> studentsInGroup = _studentRepository.getStudentListForGroup(dataHandler.getObject().getGroupId());

            for (Student student : studentsInGroup) {
                _presenceRepository.createPresence(new Presence( student.getStudentIndex(), dataHandler.getObject().getPeriodId(), PresenceStatus.UNKNOWN.toString(), null));
            }

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(), false));
        }
    }
}
