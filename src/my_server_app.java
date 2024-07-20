package src;

import java.io.*;
import java.net.*;
import java.util.Objects;

public class my_server_app {
    public static void main(String[] args) {
        //java my_server_app.java <port>
        int port = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("my_server_app is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                new client_handler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class client_handler extends Thread {
    private final Socket socket;
    private final BufferedReader receiver;

    public client_handler(Socket socket) throws IOException {
        this.socket = socket;
        this.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            System.out.println("<<< Client("+ socket.getPort() +") connected >>>");

            String message;
            while (!Objects.equals(message = receiver.readLine(), "terminate")) {
                System.out.println("Client("+ socket.getPort() +"): " + message);
            }

            receiver.close();
            socket.close();
            System.out.println("<<< Client("+ socket.getPort() +") disconnected >>>");
        } catch (IOException e) {
            System.out.println("my_server_app exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

