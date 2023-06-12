package Server;

import BusinessLogic.Interfaces.IRequestHandler;
import Shared.Entities.Student;
import Shared.Helpers.DataHandler.DataHandler;
import Shared.Helpers.JsonConverter;
import Shared.Helpers.ResponseHandler.ResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        // Client side
        DataHandler<Integer> dataHandler = new DataHandler<Integer>("GetStudentsList", 0);
        String json = JsonConverter.convertClassToJson(dataHandler);

        // Server side
        IRequestHandler requestHandler = new HandlerFactory().getHandler(json);
        ResponseHandler<ArrayList<Student>> response = (ResponseHandler<ArrayList<Student>>) requestHandler.handle(json);
        response.getData().forEach(student -> System.out.println(student.getStudentIndex()));
        String responseJson = JsonConverter.convertClassToJson(response);

        // Client side
        System.out.println(responseJson);
        // To trzeba wykonać przed funkcją i przekazac typeReference
        TypeReference<ResponseHandler<ArrayList<Student>>> typeReference = new TypeReference<ResponseHandler<ArrayList<Student>>>() {};
        ResponseHandler<ArrayList<Student>> response2 = JsonConverter.convertJsonToClass(responseJson, typeReference);
        System.out.println(response2.getData().get(0).getStudentIndex());
//        ServerSocket server = null;
//        boolean startInformation = true;
//        try {
//
//            // server is listening on port 1234
//            server = new ServerSocket(1234);
//            server.setReuseAddress(true);
//
//            // running infinite loop for getting
//            // client request
//            while (true) {
//                if (startInformation) {
//                    System.out.println("Server started");
//                    System.out.println("Waiting for client request");
//                    startInformation = false;
//                }
//
//                // socket object to receive incoming client
//                // requests
//                Socket client = server.accept();
//
//                // Displaying that new client is connected
//                // to server
//                System.out.println("New client connected"
//                        + client.getInetAddress()
//                        .getHostAddress());
//
//                // create a new thread object
//                ClientHandler clientSock
//                        = new ClientHandler(client);
//
//                // This thread will handle the client
//                // separately
//                new Thread(clientSock).start();
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (server != null) {
//                try {
//                    server.close();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}