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

    public ResponseHandler<Boolean> handle(String json) {
        try {
            TypeReference<DataHandler<CreateStudentVm>> typeReference = new TypeReference<DataHandler<CreateStudentVm>>() {};
            DataHandler<CreateStudentVm> dataHandler = JsonConverter.convertJsonToClass(json ,typeReference);

            Student student = CreateStudentVmToStudent.map(dataHandler.getObject());

            _studentRepository.createStudent(student);

            return new ResponseHandler<Boolean>(true, true);
        }
        catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return new ResponseHandler<Boolean>(false, errors, false);
        }
    }
}
