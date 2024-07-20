package src;

import java.io.*;
import java.net.*;

public class my_client_app {
    public static void main(String[] args) {
        //java my_client_app.java <hostname> <port> <role>
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String role = args[2].toUpperCase();

        try {
            Socket socket = new Socket(hostname, port);

            BufferedReader receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println(role);

            if (role.equals("SUBSCRIBER")) {
                System.out.println("You are connected as SUBSCRIBER");
                String message;
                while (true) {
                    message = receiver.readLine();
                    if (message != null) {
                        if (message.equalsIgnoreCase("terminate")){
                            System.out.println("passa");
                            break;
                        }
                        System.out.println("PUBLISHER: " + message);
                    }
                }
            } else if (role.equals("PUBLISHER")) {
                System.out.println("You are connected as PUBLISHER");
                String message;
                do {
                    System.out.print("Enter message: ");
                    message = reader.readLine();
                    writer.println(message);
                } while (!message.equalsIgnoreCase("terminate"));
            } else {
                System.out.println("You are connected as Unknown client");
                System.out.println("Please connect as either SUBSCRIBER or PUBLISHER");
            }

            writer.close();
            reader.close();
            receiver.close();
            socket.close();
            System.out.println("Connection closed");
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }}
