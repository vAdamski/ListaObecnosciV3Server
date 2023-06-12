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
    public ResponseHandler<Boolean> handle(String json) {
        try
        {
            TypeReference<DataHandler<CreatePeriodVm>> typeReference = new TypeReference<DataHandler<CreatePeriodVm>>() {};
            DataHandler<CreatePeriodVm> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            Period period = CreatePeriodVmToPeriod.map(dataHandler.getObject());

            _periodRepository.createPeriod(period);

            ArrayList<Student> studentsInGroup = _studentRepository.getStudentListForGroup(period.getGroupId());

            for (Student student : studentsInGroup) {
                _presenceRepository.createPresence(new Presence( student.getStudentIndex(), period.getPeriodId(), PresenceStatus.UNKNOWN.toString()));
            }

            return new ResponseHandler<Boolean>(true, true);
        }
        catch (Exception e)
        {
            return new ResponseHandler<Boolean>(null, e.getMessage(), false);
        }
    }
}
