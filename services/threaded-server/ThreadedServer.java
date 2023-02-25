import java.net.*;
import java.io.*;

/**
 * A threaded socket server that can handle multiple clients concurrently by creating a new thread for each connecting client.
 */
public class ThreadedServer {
    private ServerSocket serverSocket;
    private int port;

    /**
     * Constructs a new `ThreadedServer` instance with the specified port.
     *
     * @param port the port to listen on for incoming connections
     */
    public ThreadedServer(int port) {
        this.port = port;
    }

    /**
     * Starts the server and begins listening for incoming connections.
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Start a new thread to handle the client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing server socket: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ThreadedServer server = new ThreadedServer(8080);
        server.start();
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        /**
         * Handles a client connection.
         *
         * @param clientSocket the socket for the client connection
         * @throws IOException if an I/O error occurs while communicating with the client
         */
        public void run() {
            try {
                // Get the input and output streams for the socket
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                // Read the client's request
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String request = new String(buffer, 0, bytesRead);

                // Process the request and generate a response
                String response = processRequest(request);

                // Send the response back to the client
                outputStream.write(response.getBytes());

                // Close the streams and the socket
                outputStream.close();
                inputStream.close();
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                System.out.println("Client error: " + e.getMessage());
            }
        }

        private static String processRequest(String request) {
            // Process the request and generate a response
            return "Hello, " + request + "!";
        }
    }
}
