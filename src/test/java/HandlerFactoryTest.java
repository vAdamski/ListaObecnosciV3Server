import BusinessLogic.Interfaces.IRequestHandler;
import BusinessLogic.Students.Commands.CreateStudent.CreateStudentCommandHandler;
import BusinessLogic.Students.Queries.GetStudentsList.GetStudentsListQueryHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import Server.HandlerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandlerFactoryTest {
    @Test
    public void testGetHandler_WhenActionNameIsGetStudentsList_ReturnsGetStudentsListQueryHandler() throws JsonProcessingException {
        // Arrange
        String json = "{\"actionName\": \"GetStudentsList\"}";
        HandlerFactory handlerFactory = new HandlerFactory();

        // Act
        IRequestHandler handler = handlerFactory.getHandler(json);

        // Assert
        Assertions.assertTrue(handler instanceof GetStudentsListQueryHandler);
    }

    @Test
    public void testGetHandler_WhenActionNameIsCreateStudent_ReturnsCreateStudentCommandHandler() throws JsonProcessingException {
        // Arrange
        String json = "{\"actionName\": \"CreateStudent\"}";
        HandlerFactory handlerFactory = new HandlerFactory();

        // Act
        IRequestHandler handler = handlerFactory.getHandler(json);

        // Assert
        Assertions.assertTrue(handler instanceof CreateStudentCommandHandler);
    }

    @Test
    public void testGetHandler_WhenActionNameIsInvalid_ReturnsNull() throws JsonProcessingException {
        // Arrange
        String json = "{\"actionName\": \"InvalidAction\"}";
        HandlerFactory handlerFactory = new HandlerFactory();

        // Act
        IRequestHandler handler = handlerFactory.getHandler(json);

        // Assert
        Assertions.assertNull(handler);
    }
}
