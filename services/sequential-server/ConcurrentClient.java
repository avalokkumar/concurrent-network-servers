
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 A client program that connects to the concurrent server and sends requests concurrently from multiple threads.
 Each client thread sends a request to the server, receives a response and then disconnects.
 **/
public class ConcurrentClient {

    private static final int NUM_CLIENTS = 10;

    /**
     The main method that starts the concurrent clients.
     Creates a thread pool of fixed size, submits tasks to the thread pool and shuts it down once all tasks are completed.
     @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a thread pool of fixed size
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CLIENTS);
        // Submit tasks to the thread pool to simulate multiple clients
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
        // Shut down the thread pool once all tasks are completed
        executor.shutdown();
    }
}