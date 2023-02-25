import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple client program that connects to a threaded socket server and sends requests.
 */
public class ConcurrentClient {
    // The number of clients to create
    private static final int NUM_CLIENTS = 10;

    public static void main(String[] args) {
        // Create a thread pool for the clients
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CLIENTS);

        // Create a client for each thread in the pool
        for (int i = 0; i < NUM_CLIENTS; i++) {
            final int clientId = i;
            executor.submit(() -> {
                try {
                    // Connect to the server
                    Socket socket = new Socket("localhost", 8080);
                    System.out.println("Client " + clientId + " connected to server");

                    // Get the input and output streams for the socket
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    // Send a request to the server
                    out.println("Hello from client " + clientId);

                    // Read the response from the server
                    String response = in.readLine();
                    System.out.println("Client " + clientId + " received response: " + response);

                    // Close the streams and the socket
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println("Client " + clientId + " disconnected from server");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the executor when all clients are done
        executor.shutdown();
    }
}
