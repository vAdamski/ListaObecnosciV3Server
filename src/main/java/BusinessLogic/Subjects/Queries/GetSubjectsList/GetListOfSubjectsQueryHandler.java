package BusinessLogic.Subjects.Queries.GetSubjectsList;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Subject;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;

import java.util.ArrayList;

public class GetListOfSubjectsQueryHandler implements IRequestHandler {
    private final SubjectRepository _subjectRepository;
    public GetListOfSubjectsQueryHandler() {
        _subjectRepository = new SubjectRepository();
    }

    @Override
    public String handle(String json) throws Exception {
        try
        {
            ArrayList<Subject> subjects = _subjectRepository.getListOfSubjects();
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Subject>>(subjects, true));
        }
        catch (Exception e)
        {
            return JsonConverter.convertClassToJson(new ResponseHandler<ArrayList<Subject>>(null, e.getMessage(), false));
        }
    }
}
