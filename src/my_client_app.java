package src;

import java.io.*;
import java.net.*;

public class my_client_app {
    public static void main(String[] args) {
        //java my_client_app.java <server ip> <port>
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        try (Socket socket = new Socket(hostname, port);
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String message;
            do {
                System.out.print("Enter message: ");
                message = reader.readLine();
                writer.println(message);
            } while (!message.equalsIgnoreCase("terminate"));
        } catch (UnknownHostException e) {
            System.out.println("my_server_app not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
