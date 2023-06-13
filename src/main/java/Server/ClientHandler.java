package Server;

import BusinessLogic.Interfaces.IRequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    // Constructor
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            // get the outputstream of client
            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String json;
            while ((json = in.readLine()) != null) {

                // writing the received message from
                // client
                // System.out.printf(" Sent from the client: %s\n", json);

                IRequestHandler requestHandler = new HandlerFactory().getHandler(json);
                String jsonResponse = requestHandler.handle(json);

                out.println(jsonResponse);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
