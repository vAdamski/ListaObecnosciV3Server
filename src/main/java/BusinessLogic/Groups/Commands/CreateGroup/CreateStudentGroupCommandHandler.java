package BusinessLogic.Groups.Commands.CreateGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.StudentGroup;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentGroupRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class CreateStudentGroupCommandHandler implements IRequestHandler {
    StudentGroupRepository _studentGroupRepository;

    public CreateStudentGroupCommandHandler() {
        _studentGroupRepository = new StudentGroupRepository();
    }


    @Override
    public String handle(String json) throws Exception {
        try
        {
            TypeReference<DataHandler<StudentGroup>> typeReference = new TypeReference<DataHandler<StudentGroup>>() {};
            DataHandler<StudentGroup> dataHandler = JsonConverter.convertJsonToClass(json ,typeReference);

            _studentGroupRepository.createStudentGroup(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e)
        {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, errors, false));
        }
    }
}
