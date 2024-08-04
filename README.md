# Pub-Sub-Middleware

### Objective
This assignment focuses on designing and implementing a simple Publish and Subscribe middleware using Client-Server Sockets Programming concepts and techniques.

### Introduction
The assignment is related to the Publish and Subscribe (Pub/Sub) Middleware Architecture software implementations. In a Pub/Sub architecture, there are message Publishers and Subscribers. A special type of middleware amalgamates the Publishers and Subscribers in an Asynchronous mode of communication. The messages among the participants could be grouped based on unique topics where a publisher may publish on one or many topics and a subscriber may also listen to one or many topics.

### Task Outline

#### Publishers and Subscribers
1. Improve the client-server implementation for the Server to handle multiple concurrent Client connections.
2. Multiple client applications should be able to connect to the server and the typed text in a given client CLI should be displayed on the Server terminal CLI.
3. The client application should take the third command line argument to indicate whether the client will act as either “Publisher” or “Subscriber”.
   - Examples:
     - `my_client_app 192.168.10.2 5000 PUBLISHER`
     - `my_client_app 192.168.10.2 5000 SUBSCRIBER`
4. Further, the server should echo the messages sent by any “Publisher” clients to all the “Subscriber” clients’ terminals. For example, when a single server is connected with 10 client applications, when a client terminal that is in Publisher mode types a text, it should be displayed on all remaining client terminals that are connected as Subscribers.
5. The Publisher messages should be only shown on Subscriber terminals, not on any Publisher terminals.
