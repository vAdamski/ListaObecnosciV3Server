package BusinessLogic.Groups.Queries.GetListOfGroups;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.StudentGroup;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import Repositories.StudentGroupRepository;

import java.util.ArrayList;

public class GetListOfGroupsQueryHandler implements IRequestHandler {
    StudentGroupRepository _studentGroupRepository;
    public GetListOfGroupsQueryHandler() {
        _studentGroupRepository = new StudentGroupRepository();
    }

    @Override
    public ResponseHandler<ArrayList<StudentGroup>> handle(String json) {
        try
        {
            ArrayList<StudentGroup> studentGroups = _studentGroupRepository.getListOfGroups();
            return new ResponseHandler<ArrayList<StudentGroup>>(studentGroups, new ArrayList<>(), true);
        }
        catch (Exception e)
        {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            return new ResponseHandler<ArrayList<StudentGroup>>(null, errors, false);
        }
    }
}
