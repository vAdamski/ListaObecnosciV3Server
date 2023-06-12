package BusinessLogic.Groups.Commands.DeleteGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentGroupRepository;
import Repositories.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class DeleteStudentGroupCommandHandler implements IRequestHandler {
    private final StudentGroupRepository _studentGroupRepository;
    private final StudentRepository _studentRepository;
    public DeleteStudentGroupCommandHandler() {
        _studentGroupRepository = new StudentGroupRepository();
        _studentRepository = new StudentRepository();
    }

    @Override
    public ResponseHandler<Boolean> handle(String json) {
        try
        {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json ,typeReference);

            ArrayList<Student> students = _studentRepository.getStudentsList();

            for (Student student : students) {
                if (student.getGroupId() == dataHandler.getObject()) {
                    student.setGroupId(null);
                    _studentRepository.updateStudent(student);
                }
            }

            _studentGroupRepository.deleteStudentGroup(dataHandler.getObject());
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
