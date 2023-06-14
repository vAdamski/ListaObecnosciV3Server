package BusinessLogic.Students.Commands.DeleteStudentFromGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Repositories.PresenceRepository;
import Repositories.StudentRepository;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Shared.ViewModels.StudentToGroupVm;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Przechowuje logikę biznesową dotyczącą usuwania studenta z grupy.
 * Implementuje interfejs IRequestHandler.
 */
public class DeleteStudentFromGroupCommandHandler implements IRequestHandler {
    /**
     * Repozytorium studenta.
     */
    private final StudentRepository _studentRepository;
    /**
     * Repozytorium obecności.
     */
    private final PresenceRepository _presenceRepository;

    /**
     * Konstruktor klasy DeleteStudentFromGroupCommandHandler.
     * Tworzy obiekty klasy StudentRepository, PresenceRepository.
     */
    public DeleteStudentFromGroupCommandHandler() {

        _studentRepository = new StudentRepository();
        _presenceRepository = new PresenceRepository();
    }

    /**
     * Wykonuje logikę biznesową usuwania studenta z grupy.
     *
     * @param json DataHandler<StudentToGroupVm> w formacie JSON gdzie StudentToGroupVm jest obiektem zawierającym id studenta i id grupy.
     * @return ResponseHandler<Boolean> w formacie JSON.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się usunąć studenta z grupy.
     */
    @Override
    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<StudentToGroupVm>> typeReference = new TypeReference<DataHandler<StudentToGroupVm>>() {
            };
            DataHandler<StudentToGroupVm> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            _presenceRepository.deleteAllPresencesForStudent(dataHandler.getObject().getStudentIndex());

            _studentRepository.deleteStudentFromGroup(dataHandler.getObject().getStudentIndex(), dataHandler.getObject().getGroupId());


            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        } catch (Exception e) {
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, e.getMessage(), false));
        }
    }
}
