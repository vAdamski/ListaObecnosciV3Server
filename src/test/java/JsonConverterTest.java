import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonConverterTest {

    @Test
    public void convertClassToJsonTest() {
        // Given
        Student student = new Student("John", "Doe", "123456", 1);

        // When
        String json = null;
        try {
            json = JsonConverter.convertClassToJson(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then
        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.contains("\"firstName\":\"John\""));
        Assertions.assertTrue(json.contains("\"lastName\":\"Doe\""));
        Assertions.assertTrue(json.contains("\"studentIndex\":\"123456\""));
        Assertions.assertTrue(json.contains("\"groupId\":1"));
    }

    @Test
    public void convertJsonToClassTest() {
        // Given
        String json = "{\"firstName\":\"Alice\",\"lastName\":\"Smith\",\"studentIndex\":\"789012\",\"groupId\":2}";

        // When
        Student student = null;
        try {
            TypeReference<Student> typeReference = new TypeReference<Student>() {};
            student = JsonConverter.convertJsonToClass(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then
        Assertions.assertNotNull(student);
        Assertions.assertEquals("Alice", student.getFirstName());
        Assertions.assertEquals("Smith", student.getLastName());
        Assertions.assertEquals("789012", student.getStudentIndex());
        Assertions.assertEquals(Integer.valueOf(2), student.getGroupId());
    }

    @Test
    public void testConvertJsonToClass() throws Exception {
        String json = "{\"actionName\":\"action\",\"object\":{\"firstName\":\"John\",\"lastName\":\"Doe\",\"studentIndex\":\"123\",\"groupId\":1}}";

        TypeReference<DataHandler<Student>> typeReference = new TypeReference<DataHandler<Student>>() {};
        DataHandler<Student> dataHandler = JsonConverter.convertJsonToClass(json, typeReference);

        Assertions.assertEquals("action", dataHandler.getActionName());
        Student student = dataHandler.getObject();
        Assertions.assertEquals("John", student.getFirstName());
        Assertions.assertEquals("Doe", student.getLastName());
        Assertions.assertEquals("123", student.getStudentIndex());
        Assertions.assertEquals(Integer.valueOf(1), student.getGroupId());
    }

    @Test
    public void testConvertClassToJson() throws Exception {
        // Given
        DataHandler<Student> dataHandler = new DataHandler<>("update", new Student("Jane", "Smith", "54321", 2));

        // When
        String json = JsonConverter.convertClassToJson(dataHandler);

        // Then
        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.contains("\"actionName\":\"update\""));
        Assertions.assertTrue(json.contains("\"object\":{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"studentIndex\":\"54321\",\"groupId\":2}"));
    }
}
