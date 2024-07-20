package src;

import java.io.*;
import java.net.*;

public class my_client_app {
    public static void main(String[] args) {
        //java my_client_app.java <hostname> <port>
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            Socket socket = new Socket(hostname, port);

            PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connection Established");
            String message;
            do {
                System.out.print("Enter message: ");
                message = reader.readLine();
                sender.println(message);
            } while (!message.equalsIgnoreCase("terminate"));

            reader.close();
            sender.close();
            socket.close();
            System.out.println("Connection Closed");
        } catch (UnknownHostException e) {
            System.out.println("my_server_app not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
