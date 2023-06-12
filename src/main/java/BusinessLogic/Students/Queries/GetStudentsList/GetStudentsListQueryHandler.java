package BusinessLogic.Students.Queries.GetStudentsList;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Student;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentRepository;

import java.util.ArrayList;

public class GetStudentsListQueryHandler implements IRequestHandler {
    private StudentRepository _studentRepository;
    public GetStudentsListQueryHandler() {
        _studentRepository = new StudentRepository();
    }

    public ResponseHandler<ArrayList<Student>> handle(String json) {
        try {
            ArrayList<Student> students = _studentRepository.getStudentsList();
            return new ResponseHandler<ArrayList<Student>>(students, new ArrayList<>(), true);
        }catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return new ResponseHandler<ArrayList<Student>>(null, errors, false);
        }
    }
}
