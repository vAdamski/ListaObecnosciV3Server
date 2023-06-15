package Server;

import BusinessLogic.Groups.Commands.CreateGroup.CreateStudentGroupCommandHandler;
import BusinessLogic.Groups.Commands.DeleteGroup.DeleteStudentGroupCommandHandler;
import BusinessLogic.Groups.Queries.GetListOfGroups.GetListOfGroupsQueryHandler;
import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Periods.Commands.CreatePeriod.CreatePeriodCommandHandler;
import BusinessLogic.Periods.Commands.DeletePeriod.DeletePeriodCommandHandler;
import BusinessLogic.Periods.Queries.GetListForGroupAndSubject.GetListForGroupAndSubjectQueryHandler;
import BusinessLogic.Presences.Commands.UpdatePresence.UpdatePresenceCommandHandler;
import BusinessLogic.Presences.Queries.GetPresenceList.GetPresenceListQueryHandler;
import BusinessLogic.Students.Commands.AssigneStudentToGroup.AssigneStudentToGroupCommandHandler;
import BusinessLogic.Students.Commands.CreateStudent.CreateStudentCommandHandler;
import BusinessLogic.Students.Commands.DeleteStudent.DeleteStudentCommandHandler;
import BusinessLogic.Students.Commands.DeleteStudentFromGroup.DeleteStudentFromGroupCommandHandler;
import BusinessLogic.Students.Queries.GetStudentsList.GetStudentsListQueryHandler;
import BusinessLogic.Students.Queries.GetStudentsWithoutGroupList.GetStudentsWithoutGroupListQueryHandler;
import BusinessLogic.Subjects.Commands.CreateSubject.CreateSubjectCommandHandler;
import BusinessLogic.Subjects.Commands.DeleteSubject.DeleteSubjectCommandHandler;
import BusinessLogic.Subjects.Queries.GetSubjectsList.GetListOfSubjectsQueryHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Klasa `HandlerFactory` jest fabryką obsługującą tworzenie odpowiednich obiektów obsługujących żądania na podstawie otrzymanego JSON-a.
 */
public class HandlerFactory {
    /**
     * Metoda `getHandler` zwraca obiekt implementujący interfejs `IRequestHandler` na podstawie otrzymanego JSON-a.
     *
     * @param json JSON zawierający informacje o żądaniu.
     * @return Obiekt implementujący interfejs `IRequestHandler` odpowiadający żądaniu.
     * @throws JsonProcessingException Występuje w przypadku problemów z przetwarzaniem JSON-a.
     */
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
            case "GetStudentsWithoutGroupList":
                return new GetStudentsWithoutGroupListQueryHandler();
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
                return new GetPresenceListQueryHandler();
            case "UpdatePresence":
                return new UpdatePresenceCommandHandler();
            case "CreateSubject":
                return new CreateSubjectCommandHandler();
            case "DeleteSubject":
                return new DeleteSubjectCommandHandler();
            default:
                return null;
        }
    }
}
