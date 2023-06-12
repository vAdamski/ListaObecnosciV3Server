package BusinessLogic.Groups.Commands.CreateGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Mappers.CreateStudentGroupVmToStudentGroup;
import BusinessLogic.Mappers.CreateStudentVmToStudent;
import Shared.Entities.StudentGroup;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentGroupRepository;
import Shared.ViewModels.CreateStudentGroupVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class CreateStudentGroupCommandHandler implements IRequestHandler {
    StudentGroupRepository _studentGroupRepository;

    public CreateStudentGroupCommandHandler() {
        _studentGroupRepository = new StudentGroupRepository();
    }


    @Override
    public ResponseHandler<Boolean> handle(String json) {
        try
        {
            TypeReference<DataHandler<CreateStudentGroupVm>> typeReference = new TypeReference<DataHandler<CreateStudentGroupVm>>() {};
            DataHandler<CreateStudentGroupVm> dataHandler = JsonConverter.convertJsonToClass(json ,typeReference);

            StudentGroup studentGroup = CreateStudentGroupVmToStudentGroup.map(dataHandler.getObject());

            _studentGroupRepository.createStudentGroup(studentGroup);

            return new ResponseHandler<Boolean>(true, true);
        }
        catch (Exception e)
        {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return new ResponseHandler<Boolean>(false, errors, false);
        }
    }
}
