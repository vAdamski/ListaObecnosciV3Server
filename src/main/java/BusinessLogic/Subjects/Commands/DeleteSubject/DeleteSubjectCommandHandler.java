package BusinessLogic.Subjects.Commands.DeleteSubject;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeleteSubjectCommandHandler implements IRequestHandler {
    private final SubjectRepository _subjectRepository;
    public DeleteSubjectCommandHandler() {
        _subjectRepository = new SubjectRepository();
    }

    @Override
    public ResponseHandler<Boolean> handle(String json) {
        try {
            TypeReference<DataHandler<Integer>> typeReference = new TypeReference<DataHandler<Integer>>() {};
            DataHandler<Integer> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

            //TODO: Usunąć powiązania z studentami w tabeli Student - Subject

            _subjectRepository.deleteSubject(dataHandler.getObject());

            return new ResponseHandler<Boolean>(true, true);
        } catch (Exception e) {
            return new ResponseHandler<Boolean>(false, e.getMessage(), false);
        }
    }
}
