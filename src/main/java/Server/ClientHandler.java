package Server;

import BusinessLogic.Interfaces.IRequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa `ClientHandler` implementuje interfejs `Runnable` i obsługuje żądania klienta.
 * Odbiera dane od klienta, przetwarza je i odpowiada na żądania.
 */
public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    /**
     * Konstruktor klasy ClientHandler.
     *
     * @param socket Socket klienta.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Metoda run, implementująca interfejs Runnable, obsługuje żądania klienta.
     */
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String json;
            while ((json = in.readLine()) != null) {
                System.out.printf("Odebrane od klienta: %s\n", json);

                // Utworzenie odpowiedniego obiektu obsługującego żądanie na podstawie otrzymanego JSON-a
                IRequestHandler requestHandler = new HandlerFactory().getHandler(json);

                // Przetworzenie żądania i otrzymanie odpowiedzi w postaci JSON-a
                String jsonResponse = requestHandler.handle(json);

                // Wysłanie odpowiedzi do klienta
                out.println(jsonResponse);
            }
        } catch (IOException e) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

