package BusinessLogic.Subjects.Commands.CreateSubject;

import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Mappers.CreateSubjectVmToSubject;
import Shared.Entities.Subject;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;
import Shared.ViewModels.CreateSubjectVm;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class CreateSubjectCommandHandler implements IRequestHandler {
    public final SubjectRepository _subjectRepository;

    public CreateSubjectCommandHandler() {

        _subjectRepository = new SubjectRepository();
    }

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
