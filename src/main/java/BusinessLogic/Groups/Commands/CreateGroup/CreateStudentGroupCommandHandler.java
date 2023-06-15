package BusinessLogic.Groups.Commands.CreateGroup;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.StudentGroup;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentGroupRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą tworzenia grupy.
 * Implementuje interfejs IRequestHandler.
 */
public class CreateStudentGroupCommandHandler implements IRequestHandler {
    /**
     * Repozytorium grupy.
     */
    private final StudentGroupRepository _studentGroupRepository;

    /**
     * Konstruktor klasy CreateStudentGroupCommandHandler.
     * Tworzy obiekt klasy StudentGroupRepository.
     */
    public CreateStudentGroupCommandHandler() {
        _studentGroupRepository = new StudentGroupRepository();
    }

    /**
     * Wykonuje logikę biznesową tworzenia grupy.
     *
     * @param json DataHandler<StudentGroup> w formacie JSON gdzie StudentGroup jest obiektem grupy.
     * @return ResponseHandler<Boolean> w formacie JSON zawierający informację o powodzeniu lub niepowodzeniu operacji.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się utworzyć grupy.
     */
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
