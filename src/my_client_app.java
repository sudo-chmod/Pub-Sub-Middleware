package src;

import java.io.*;
import java.net.*;

public class my_client_app {
    public static void main(String[] args) {
        //java my_client_app.java <hostname> <port> <role> <subject>
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String role = args[2].toUpperCase();
        String subject = args[3].toUpperCase();

        try {
            Socket socket = new Socket(hostname, port);

            BufferedReader receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println(role);
            writer.println(subject);

            if (role.equals("SUBSCRIBER")) {
                System.out.println("Connection Established as SUBSCRIBER to " + subject);
                String message;
                while (true) {
                    message = receiver.readLine();
                    if (message != null) {
                        if (message.equalsIgnoreCase("terminate")){
                            break;
                        }
                        if (message.equals("terminateCondition_SubjectNotFound")){
                            String availableSubjects = receiver.readLine();
                            System.out.println("Please subscribe to a available SUBJECT!");
                            System.out.println("Available SUBJECT: " + availableSubjects);
                            break;
                        }
                        System.out.println("PUBLISHER: " + message);
                    }
                }
            } else if (role.equals("PUBLISHER")) {
                System.out.println("Connection Established as PUBLISHER to " + subject);
                String message;
                do {
                    System.out.print("Enter message: ");
                    message = reader.readLine();
                    writer.println(message);
                } while (!message.equalsIgnoreCase("terminate"));
            } else {
                System.out.println("Connection Established as Unknown client");
                System.out.println("Please connect as either SUBSCRIBER or PUBLISHER!");
            }

            writer.close();
            reader.close();
            receiver.close();
            socket.close();
            System.out.println("Connection Closed");
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
