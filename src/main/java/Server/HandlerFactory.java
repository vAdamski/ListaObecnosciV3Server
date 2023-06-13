package Server;

import BusinessLogic.Groups.Commands.CreateGroup.CreateStudentGroupCommandHandler;
import BusinessLogic.Groups.Commands.DeleteGroup.DeleteStudentGroupCommandHandler;
import BusinessLogic.Groups.Queries.GetListOfGroups.GetListOfGroupsQueryHandler;
import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Students.Commands.CreateStudent.CreateStudentCommandHandler;
import BusinessLogic.Students.Commands.DeleteStudent.DeleteStudentCommandHandler;
import BusinessLogic.Students.Queries.GetStudentsList.GetStudentsListQueryHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandlerFactory {
    public IRequestHandler getHandler(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String actionName = jsonNode.get("actionName").asText();

        switch (actionName) {
            case "GetStudentsList":
                return new GetStudentsListQueryHandler();
            case "CreateStudent":
                return new CreateStudentCommandHandler();
            case "DeleteStudent":
                return new DeleteStudentCommandHandler();
            case "CreateStudentGroup":
                return new CreateStudentGroupCommandHandler();
            case "DeleteStudentGroup":
                return new DeleteStudentGroupCommandHandler();
            case "GetStudentGroupList":
                return new GetListOfGroupsQueryHandler();
                
            default:
                return null;
        }
    }
}
