package src;

import java.io.*;
import java.net.*;
import java.util.*;

public class my_server_app {
    private static final List<client_handler> subscriberList = new ArrayList<>();

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

    private static class client_handler extends Thread {
        private final Socket socket;
        private final BufferedReader receiver;
        private final PrintWriter sender;

        public client_handler(Socket socket) throws IOException {
            this.socket = socket;
            this.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.sender = new PrintWriter(socket.getOutputStream(), true);
        }

        public void run() {
            try {
                String role = receiver.readLine();

                if (role.equalsIgnoreCase("PUBLISHER")) {
                    System.out.println("<<< PUBLISHER("+ socket.getPort() +") connected >>>");
                    String message;
                    while (!Objects.equals(message = receiver.readLine(), "terminate")) {
                        System.out.println("PUBLISHER(" + socket.getPort() + "): " + message);
                        for (client_handler subscriber : subscriberList) {
                            synchronized (subscriber.sender) {
                                subscriber.sender.println(message);
                            }
                        }
                    }
                } else if (role.equalsIgnoreCase("SUBSCRIBER")) {
                    System.out.println("<<< SUBSCRIBER("+ socket.getPort() +") connected >>>");
                    subscriberList.add(this);
                    while (receiver.readLine() != null) {}
                } else {
                    System.out.println("<<< Unknown client("+ socket.getPort() +") connected >>>");
                }

                sender.close();
                receiver.close();
                socket.close();

                if (role.equalsIgnoreCase("PUBLISHER")) {
                    System.out.println("<<< PUBLISHER("+ socket.getPort() +") disconnected >>>");
                } else if (role.equalsIgnoreCase("SUBSCRIBER")) {
                    subscriberList.remove(this);
                    System.out.println("<<< SUBSCRIBER("+ socket.getPort() +") disconnected >>>");
                } else {
                    System.out.println("<<< Unknown client("+ socket.getPort() +") disconnected >>>");
                }
            } catch (IOException e) {
                System.out.println("my_server_app exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
