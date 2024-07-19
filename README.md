# Pub-Sub-Middleware

### Objective
This assignment focuses on designing and implementing a simple Publish and Subscribe middleware using Client-Server Sockets Programming concepts and techniques.

### Introduction
The assignment is related to the Publish and Subscribe (Pub/Sub) Middleware Architecture software implementations. In a Pub/Sub architecture, there are message Publishers and Subscribers. A special type of middleware amalgamates the Publishers and Subscribers in an Asynchronous mode of communication. The messages among the participants could be grouped based on unique topics where a publisher may publish on one or many topics and a subscriber may also listen to one or many topics.

### Assignment Outline

#### Task 1: The Client-Server Application
1. Implement a client-server socket application using the selected programming language. The server will listen for connections on a pre-defined PORT where the client will use the server IP and the PORT to connect to the server.
2. The implementation only needs a Command Line Interface (CLI) to show the activities between the Server and the Client applications. Using a Graphical User Interface (GUI) based implementation will not influence the assignment to be graded higher than a CLI-based implementation.
3. Start the Server by passing the PORT as a command line argument.
   - Example: `my_server_app 5000`
4. Start a Client to connect with the server by passing Server IP and Server PORT as command line arguments.
   - Example: `my_client_app 192.168.10.2 5000`
5. After starting the Client, whatever text is typed on the client CLI should be displayed on the server-side CLI as standard system output text.
6. The primary objective is to demonstrate the client and server communicating with each other.
7. The client should perpetually run until the “terminate” keyword is typed by a user. After typing the keyword, the client should disconnect from the server and terminate.

#### Task 2: Publishers and Subscribers
1. Improve the client-server implementation for the Server to handle multiple concurrent Client connections.
2. Multiple client applications should be able to connect to the server and the typed text in a given client CLI should be displayed on the Server terminal CLI.
3. The client application should take the third command line argument to indicate whether the client will act as either “Publisher” or “Subscriber”.
   - Examples:
     - `my_client_app 192.168.10.2 5000 PUBLISHER`
     - `my_client_app 192.168.10.2 5000 SUBSCRIBER`
4. Further, the server should echo the messages sent by any “Publisher” clients to all the “Subscriber” clients’ terminals. For example, when a single server is connected with 10 client applications, when a client terminal that is in Publisher mode types a text, it should be displayed on all remaining client terminals that are connected as Subscribers.
5. The Publisher messages should be only shown on Subscriber terminals, not on any Publisher terminals.

#### Task 3: Publishers and Subscribers Filtered on Topics/Subjects
1. Improve the implementation of Task 2 to include “topic/subject” based filtering of messages among Publishers and Subscribers.
2. The client implementation should be improved to include the fourth command line argument of the topic/subject of either the publisher or subscriber.
   - Examples:
     - `my_client_app 192.168.10.2 5000 PUBLISHER TOPIC_A`
     - `my_client_app 192.168.10.2 5000 SUBSCRIBER TOPIC_A`
3. The client application that is a publisher should be sending the messages to the server on a specific topic/subject to route to interested subscriber clients on the same topic/subject.
4. There can be publishers and subscribers interested in different messages connected concurrently to the server.

### Branch Strategy
To manage the development of each task efficiently, we will create a separate branch for each task:
- `task-1`: For Task 1 implementation
- `task-2`: For Task 2 implementation
- `task-3`: For Task 3 implementation
