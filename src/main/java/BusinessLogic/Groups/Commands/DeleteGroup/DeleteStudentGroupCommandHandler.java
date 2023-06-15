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

/**
 * Przechowuje logikę biznesową dotyczącą usuwania studenta z grupy.
 * Implementuje interfejs IRequestHandler.
 */
public class DeleteStudentGroupCommandHandler implements IRequestHandler {
    /**
     * Repozytorium grupy.
     */
    private final StudentGroupRepository _studentGroupRepository;
    /**
     * Repozytorium studenta.
     */
    private final StudentRepository _studentRepository;
    /**
     * Konstruktor klasy DeleteStudentGroupCommandHandler.
     * Tworzy obiekt klasy StudentGroupRepository i StudentRepository.
     */
    public DeleteStudentGroupCommandHandler() {
        _studentGroupRepository = new StudentGroupRepository();
        _studentRepository = new StudentRepository();
    }

    /**
     * Wykonuje logikę biznesową usuwania studenta z grupy.
     *
     * @param json String DataHandler<Integer> w formacie JSON gdzie Integer jest id grupy.
     * @return String ResponseHandler<Boolean> w formacie JSON zawierający informację o powodzeniu lub niepowodzeniu operacji.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się usunąć studenta z grupy.
     */
    @Override
    public String handle(String json) throws Exception {
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
