# Pub-Sub-Middleware

### Objective
This assignment focuses on designing and implementing a simple Publish and Subscribe middleware using Client-Server Sockets Programming concepts and techniques.

### Introduction
The assignment is related to the Publish and Subscribe (Pub/Sub) Middleware Architecture software implementations. In a Pub/Sub architecture, there are message Publishers and Subscribers. A special type of middleware amalgamates the Publishers and Subscribers in an Asynchronous mode of communication. The messages among the participants could be grouped based on unique topics where a publisher may publish on one or many topics and a subscriber may also listen to one or many topics.

### Task Outline

#### The Client-Server Application
1. Implement a client-server socket application using the selected programming language. The server will listen for connections on a pre-defined PORT where the client will use the server IP and the PORT to connect to the server.
2. The implementation only needs a Command Line Interface (CLI) to show the activities between the Server and the Client applications. Using a Graphical User Interface (GUI) based implementation will not influence the assignment to be graded higher than a CLI-based implementation.
3. Start the Server by passing the PORT as a command line argument.
   - Example: `my_server_app 5000`
4. Start a Client to connect with the server by passing Server IP and Server PORT as command line arguments.
   - Example: `my_client_app 192.168.10.2 5000`
5. After starting the Client, whatever text is typed on the client CLI should be displayed on the server-side CLI as standard system output text.
6. The primary objective is to demonstrate the client and server communicating with each other.
7. The client should perpetually run until the “terminate” keyword is typed by a user. After typing the keyword, the client should disconnect from the server and terminate.
