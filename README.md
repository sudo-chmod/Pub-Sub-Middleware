# Pub-Sub-Middleware

### Objective
This assignment focuses on designing and implementing a simple Publish and Subscribe middleware using Client-Server Sockets Programming concepts and techniques.

### Introduction
The assignment is related to the Publish and Subscribe (Pub/Sub) Middleware Architecture software implementations. In a Pub/Sub architecture, there are message Publishers and Subscribers. A special type of middleware amalgamates the Publishers and Subscribers in an Asynchronous mode of communication. The messages among the participants could be grouped based on unique topics where a publisher may publish on one or many topics and a subscriber may also listen to one or many topics.

### Task Outline

#### Publishers and Subscribers Filtered on Topics/Subjects
1. Improve the implementation of Task 2 to include “topic/subject” based filtering of messages among Publishers and Subscribers.
2. The client implementation should be improved to include the fourth command line argument of the topic/subject of either the publisher or subscriber.
   - Examples:
     - `my_client_app 192.168.10.2 5000 PUBLISHER TOPIC_A`
     - `my_client_app 192.168.10.2 5000 SUBSCRIBER TOPIC_A`
3. The client application that is a publisher should be sending the messages to the server on a specific topic/subject to route to interested subscriber clients on the same topic/subject.
4. There can be publishers and subscribers interested in different messages connected concurrently to the server.
