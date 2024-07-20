package src;

import java.io.*;
import java.net.*;
import java.util.*;

public class my_server_app {
    private static final Map<String, Map<String, List<client_handler>>> subjectClientList = new HashMap<>();

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
                String subject = receiver.readLine();
                boolean subjectNotFoundError = false;

                if (role.equalsIgnoreCase("PUBLISHER")) {
                    if (!subjectClientList.containsKey(subject)) {
                        subjectClientList.putIfAbsent(subject, new HashMap<>());
                        subjectClientList.get(subject).putIfAbsent("PUBLISHER", new ArrayList<>());
                        subjectClientList.get(subject).putIfAbsent("SUBSCRIBER", new ArrayList<>());
                        System.out.println("New SUBJECT added: " + subject);
                        System.out.print("Available SUBJECT List: ");
                        StringBuilder subjects = new StringBuilder();
                        for (String availableSubject : subjectClientList.keySet()) {
                            if (!subjects.isEmpty()) {
                                subjects.append(", ");
                            }
                            subjects.append(availableSubject);
                        }
                        System.out.println(subjects.toString());
                    }

                    System.out.println("<<< PUBLISHER("+ socket.getPort() +") connected to " + subject + " >>>");
                    subjectClientList.get(subject).get("PUBLISHER").add(this);
                    String message;
                    while (!Objects.equals(message = receiver.readLine(), "terminate")) {
                        System.out.println("PUBLISHER(" + socket.getPort() + ") to " + subject + ": " + message);
                        if (subjectClientList.get(subject).get("SUBSCRIBER") != null) {
                            for (client_handler subscriber : subjectClientList.get(subject).get("SUBSCRIBER")) {
                                synchronized (subscriber.sender) {
                                    subscriber.sender.println(message);
                                }
                            }
                        }
                    }
                } else if (role.equalsIgnoreCase("SUBSCRIBER")) {
                    if(!subjectClientList.containsKey(subject)) {
                        System.out.println("<<< SUBSCRIBER("+ socket.getPort() +") connected to UNKNOWN SUBJECT >>>");
                        subjectNotFoundError = true;
                        StringBuilder subjects = new StringBuilder();
                        for (String availableSubject : subjectClientList.keySet()) {
                            if (!subjects.isEmpty()) {
                                subjects.append(", ");
                            }
                            subjects.append(availableSubject);
                        }
                        sender.println("terminateCondition_SubjectNotFound");
                        sender.println(subjects);
                    } else {
                        System.out.println("<<< SUBSCRIBER("+ socket.getPort() +") connected to " + subject + " >>>");
                        subjectClientList.get(subject).get("SUBSCRIBER").add(this);
                        while (receiver.readLine() != null) {}
                    }
                } else {
                    System.out.println("<<< Unknown client("+ socket.getPort() +") connected >>>");
                }

                sender.close();
                receiver.close();
                socket.close();

                if (role.equalsIgnoreCase("PUBLISHER")) {
                    subjectClientList.get(subject).get("PUBLISHER").remove(this);
                    System.out.println("<<< PUBLISHER("+ socket.getPort() +") disconnected >>>");
                } else if (role.equalsIgnoreCase("SUBSCRIBER")) {
                    if (!subjectNotFoundError) {
                        subjectClientList.get(subject).get("SUBSCRIBER").remove(this);
                    }
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
