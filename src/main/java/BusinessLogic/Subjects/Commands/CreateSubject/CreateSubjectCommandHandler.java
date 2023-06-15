package BusinessLogic.Subjects.Commands.CreateSubject;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Subject;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

/**
 * Przechowuje logikę biznesową dotyczącą tworzenia przedmiotu.
 * Implementuje interfejs IRequestHandler.
 */
public class CreateSubjectCommandHandler implements IRequestHandler {
    /**
     * Repozytorium przedmiotu.
     */
    public final SubjectRepository _subjectRepository;

    /**
     * Konstruktor klasy CreateSubjectCommandHandler.
     * Tworzy obiekt klasy SubjectRepository.
     */
    public CreateSubjectCommandHandler() {

        _subjectRepository = new SubjectRepository();
    }

    /**
     * Wykonuje logikę biznesową tworzenia przedmiotu.
     *
     * @param json DataHandler<Subject> w formacie JSON gdzie Subject jest obiektem przedmiotu.
     * @return ResponseHandler<Boolean> w formacie JSON zawierający informację o powodzeniu lub niepowodzeniu operacji.
     * @throws Exception Wyjątek zgłaszany, gdy nie udało się utworzyć przedmiotu.
     */
    @Override
    public String handle(String json) throws Exception {
        try {
            TypeReference<DataHandler<Subject>> typeReference = new TypeReference<DataHandler<Subject>>() {};
            DataHandler<Subject> dataHandler = JsonConverter.convertJsonToClass(json ,typeReference);

            _subjectRepository.createSubject(dataHandler.getObject());

            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(true, true));
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return JsonConverter.convertClassToJson(new ResponseHandler<Boolean>(false, errors, false));
        }
    }
}
