package com.clay.sequential_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 This class represents a sequential server that can handle a single client at any given time.
 When a client connects, the server enters the state machine to handle the client's request.
 */
public class Server {

    private ServerSocket serverSocket;

    /**
     Creates a new instance of the Server class that listens on the specified port.
     @param port The port on which the server should listen for incoming client connections.
     @throws IOException If an error occurs while creating the server socket.
     */
    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    /**
     Starts the server and listens for incoming client connections indefinitely.
     When a client connects, the handleClient() method is called to handle the client's request.
     @throws IOException If an error occurs while waiting for a client connection or handling the client's request.
     */
    public void run() throws IOException {
        while (true) {
            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();

            // Process the client's request
            handleClient(clientSocket);
        }
    }

    /**
     Handles the client's request by reading the request from the input stream,
     processing the request using the processRequest() method, sending the response
     back to the client, and closing the streams and socket.
     @param clientSocket The socket that the client is connected to.
     @throws IOException If an error occurs while reading from or writing to the socket.
     */
    private void handleClient(Socket clientSocket) throws IOException {
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
    }

    /**
     Processes the client's request and generates a response.
     @param request The client's request.
     @return A response to the client's request.
     */
    private String processRequest(String request) {
        // Process the request and generate a response
        return "Hello, " + request + "!";
    }

    /**
     The main method that creates an instance of the Server class and starts the server.
     @param args The command-line arguments.
     @throws IOException If an error occurs while creating the server or handling client requests.
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.run();
    }
}
