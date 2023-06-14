public class StudentHandlerTest {
//    @Test
//    public void createDeleteGetStudentTestsShouldReturnTrue() throws Exception {
//        // Arrange
//        // Create student
//        CreateStudentVm student = new CreateStudentVm("John", "Doe", "999999");
//        DataHandler<CreateStudentVm> dataHandlerCreate = new DataHandler<>("CreateStudent", student);
//        String jsonCreate = JsonConverter.convertClassToJson(dataHandlerCreate);
//
//        // Delete student
//        DataHandler<String> dataHandlerDelete = new DataHandler<>("DeleteStudent", "999999");
//        String jsonDelete = JsonConverter.convertClassToJson(dataHandlerDelete);
//
//        // Get student
//        DataHandler<String> dataHandlerGet = new DataHandler<>("GetStudentsList", "");
//        String jsonGet = JsonConverter.convertClassToJson(dataHandlerGet);
//
//        IRequestHandler requestHandlerCreate = new HandlerFactory().getHandler(jsonCreate);
//        IRequestHandler requestHandlerDelete = new HandlerFactory().getHandler(jsonDelete);
//        IRequestHandler requestHandlerGet = new HandlerFactory().getHandler(jsonGet);
//
//        // Act
//        ResponseHandler<Boolean> responseCreate = (ResponseHandler<Boolean>) requestHandlerCreate.handle(jsonCreate);
//        ResponseHandler<ArrayList<Student>> responseGet = (ResponseHandler<ArrayList<Student>>) requestHandlerGet.handle(jsonGet);
//        ResponseHandler<Boolean> responseDelete = (ResponseHandler<Boolean>) requestHandlerDelete.handle(jsonDelete);
//
//        // Assert
//        Assertions.assertTrue(responseCreate.getData());
//        Assertions.assertTrue(responseDelete.getData());
//        Boolean isCreatedStudentInList = false;
//        for (Student s : responseGet.getData()) {
//            if (s.getStudentIndex().equals(student.getStudentIndex())) {
//                isCreatedStudentInList = true;
//            }
//        }
//        Assertions.assertTrue(isCreatedStudentInList);
//    }
}
