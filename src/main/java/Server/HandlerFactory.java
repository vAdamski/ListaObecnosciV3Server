package Server;

import BusinessLogic.Groups.Commands.CreateGroup.CreateStudentGroupCommandHandler;
import BusinessLogic.Groups.Commands.DeleteGroup.DeleteStudentGroupCommandHandler;
import BusinessLogic.Groups.Queries.GetListOfGroups.GetListOfGroupsQueryHandler;
import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Periods.Commands.CreatePeriod.CreatePeriodCommandHandler;
import BusinessLogic.Periods.Commands.DeletePeriod.DeletePeriodCommandHandler;
import BusinessLogic.Periods.Queries.GetListForGroupAndSubject.GetListForGroupAndSubjectQueryHandler;
import BusinessLogic.Students.Commands.AssigneStudentToGroup.AssigneStudentToGroupCommandHandler;
import BusinessLogic.Students.Commands.CreateStudent.CreateStudentCommandHandler;
import BusinessLogic.Students.Commands.DeleteStudent.DeleteStudentCommandHandler;
import BusinessLogic.Students.Commands.DeleteStudentFromGroup.DeleteStudentFromGroupCommandHandler;
import BusinessLogic.Students.Queries.GetStudentsList.GetStudentsListQueryHandler;
import BusinessLogic.Subjects.Queries.GetSubjectsList.GetListOfSubjectsQueryHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandlerFactory {
    public IRequestHandler getHandler(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String actionName = jsonNode.get("actionName").asText();

        switch (actionName) {
            case "CreateStudent":
                return new CreateStudentCommandHandler();
            case "DeleteStudent":
                return new DeleteStudentCommandHandler();
            case "GetStudentsList":
                return new GetStudentsListQueryHandler();
            case "GetStudentGroupList":
                return new GetListOfGroupsQueryHandler();
            case "CreateStudentGroup":
                return new CreateStudentGroupCommandHandler();
            case "DeleteStudentGroup":
                return new DeleteStudentGroupCommandHandler();
            case "AssigneStudentToGroup":
                return new AssigneStudentToGroupCommandHandler();
            case "DeleteStudentFromGroup":
                return new DeleteStudentFromGroupCommandHandler();
            case "AssignePeriodToGroup":
                return new CreatePeriodCommandHandler();
            case "GetListForGroupAndSubject":
                return new GetListForGroupAndSubjectQueryHandler();
            case "GetSubjectsList":
                return new GetListOfSubjectsQueryHandler();
            case "DeletePeriod":
                return new DeletePeriodCommandHandler();
            case "GetPresenceList":
                return null;
            case "UpdatePresence":
                return null;
            case "CreateSubject":
                return null;
            case "DeleteSubject":
                return null;
            default:
                return null;
        }
    }
}
