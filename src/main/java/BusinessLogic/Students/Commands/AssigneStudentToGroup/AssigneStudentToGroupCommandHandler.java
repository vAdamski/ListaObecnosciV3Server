package BusinessLogic.Students.Commands.AssigneStudentToGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.StudentRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.StudentToGroupVm;
import com.fasterxml.jackson.core.type.TypeReference;

public class AssigneStudentToGroupCommandHandler implements IRequestHandler {
    private final StudentRepository _studentRepository;

    public AssigneStudentToGroupCommandHandler() {

        _studentRepository = new StudentRepository();
    }

    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<StudentToGroupVm>> typeReference = new TypeReference<DataHandler<StudentToGroupVm>>() {};
            DataHandler<StudentToGroupVm> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _studentRepository.assigneStudentToGroup(dataHandler.getObject().getStudentIndex(), dataHandler.getObject().getGroupId());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(),false));
        }
    }
}
