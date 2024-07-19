package src;

import java.io.*;
import java.net.*;
import java.util.Objects;

public class my_server_app {
    public static void main(String[] args) {
        //java my_server_app.java <port>
        int port = Integer.parseInt(args[0]);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("my_server_app is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("<<< Client("+ socket.getPort() +") connected >>>");
                new client_handler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class client_handler extends Thread {
    private final Socket socket;

    public client_handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String message;
            while (!Objects.equals(message = reader.readLine(), "terminate")) {
                System.out.println("Client("+ socket.getPort() +"): " + message);
            }
        } catch (IOException e) {
            System.out.println("my_server_app exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                System.out.println("<<< Client("+ socket.getPort() +") disconnected >>>");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
