package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa `Main` zawiera metodę `main` jako punkt wejścia programu.
 * Odpowiada za uruchomienie serwera i obsługę przychodzących klientów.
 */
public class Main {
    /**
     * Metoda `main` uruchamia serwer i obsługuje przychodzących klientów.
     *
     * @param args Argumenty wiersza poleceń.
     * @throws Exception W przypadku wystąpienia błędu.
     */
    public static void main(String[] args) throws Exception {
        boolean startInformation = true;
        ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            // Uruchomienie nieskończonej pętli w celu oczekiwania na żądanie klienta
            while (true) {
                if (startInformation) {
                    System.out.println("Serwer został uruchomiony");
                    System.out.println("Oczekiwanie na żądanie klienta");
                    startInformation = false;
                }

                // Obiekt socket do odbierania przychodzących żądań klienta
                Socket client = server.accept();

                // Wyświetlanie informacji o połączonym nowym kliencie
                System.out.println("Nowy klient połączony: " + client.getInetAddress().getHostAddress());

                // Utworzenie nowego wątku obsługującego klienta
                ClientHandler clientSock = new ClientHandler(client);

                // Ten wątek będzie obsługiwać klienta oddzielnie
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
