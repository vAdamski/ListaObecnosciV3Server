package BusinessLogic.Students.Commands.DeleteStudent;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class DeleteStudentCommandHandler implements IRequestHandler {
    private StudentRepository _studentRepository;
    public DeleteStudentCommandHandler()
    {
        _studentRepository = new StudentRepository();
    }

    @Override
    public ResponseHandler<Boolean> handle(String json) {
        try
        {
            TypeReference<DataHandler<String>> typeReference = new TypeReference<DataHandler<String>>() {};
            DataHandler<String> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);
            _studentRepository.deleteStudent(dataHandler.getObject());

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
