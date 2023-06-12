package BusinessLogic.Subjects.Queries.GetSubjectsList;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Subject;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.SubjectRepository;

import java.util.ArrayList;

public class GetListOfSubjectsQueryHandler implements IRequestHandler {
    private final SubjectRepository _subjectRepository;
    public GetListOfSubjectsQueryHandler() {
        _subjectRepository = new SubjectRepository();
    }

    @Override
    public ResponseHandler<ArrayList<Subject>> handle(String json) {
        try
        {
            ArrayList<Subject> subjects = _subjectRepository.getListOfSubjects();
            return new ResponseHandler<ArrayList<Subject>>(subjects, true);
        }
        catch (Exception e)
        {
            return new ResponseHandler<ArrayList<Subject>>(null, e.getMessage(), false);
        }
    }
}
