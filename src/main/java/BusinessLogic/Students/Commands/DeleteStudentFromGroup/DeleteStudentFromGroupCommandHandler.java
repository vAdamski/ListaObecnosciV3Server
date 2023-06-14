package BusinessLogic.Students.Commands.DeleteStudentFromGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Repositories.StudentRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.StudentToGroupVm;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeleteStudentFromGroupCommandHandler implements IRequestHandler {
    private final StudentRepository _studentRepository;
    private final PresenceRepository _presenceRepository;
    public DeleteStudentFromGroupCommandHandler() {

        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
    }

    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<StudentToGroupVm>> typeReference = new TypeReference<DataHandler<StudentToGroupVm>>() {};
            DataHandler<StudentToGroupVm> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _presenceRepository.deleteAllPresencesForStudent(dataHandler.getObject().getStudentIndex());

            _studentRepository.deleteStudentFromGroup(dataHandler.getObject().getStudentIndex(), dataHandler.getObject().getGroupId());



            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(),false));
        }
    }
}
