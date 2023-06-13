package BusinessLogic.Students.Commands.CreateStudent;
import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Mappers.CreateStudentVmToStudent;
import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentRepository;
import Shared.ViewModels.CreateStudentVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class CreateStudentCommandHandler implements IRequestHandler {
    private StudentRepository _studentRepository;

    public CreateStudentCommandHandler() {
        _studentRepository = new StudentRepository();
    }

    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<Student>> typeReference = new TypeReference<DataHandler<Student>>() {};
            DataHandler<Student> dataHandler = JsonConverter.convertJsonToClass(json ,typeReference);

            _studentRepository.createStudent(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        }
        catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, errors, false));
        }
    }
}
