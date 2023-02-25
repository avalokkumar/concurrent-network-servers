### Actor model

* The Actor model is a concurrency model that treats actors as the fundamental unit of computation.
* Actors are independent entities that encapsulate both state and behavior, and communicate with each other by exchanging messages.
* Each actor runs in its own thread of control and has a mailbox to which other actors can send messages.
* Actors can only modify their own state, and all communication between actors is asynchronous.
* Actors are able to create new actors, which can be used to create more complex systems.
* The Actor model provides a high degree of concurrency and scalability, and is well-suited for systems with a large number of independent, autonomous components.
* In the Actor model, there is no shared state between actors, which reduces the possibility of race conditions and other concurrency issues.
* Actors can also be used to implement fault-tolerant systems, since a failed actor can be automatically restarted by its supervisor actor.
* The Actor model is based on the principles of message-passing and isolation, and emphasizes the importance of modularity and composability in software design.


#### Examples of Actor model

* > **Akka:** a popular open-source toolkit and runtime for building concurrent, distributed, and fault-tolerant systems in Java and Scala. Akka is designed to be used with the Actor model, providing a message-driven programming model for building highly scalable and fault-tolerant applications.

* > **Erlang:** a programming language and runtime system designed for building concurrent and distributed systems. Erlang uses the Actor model for concurrency and message-passing, and is particularly well-suited for building systems that require high availability and fault tolerance.

* > **Orleans:** an open-source framework for building distributed systems in .NET. Orleans uses the Actor model for concurrency and provides a programming model that abstracts away many of the complexities of building distributed systems, making it easier to write scalable and reliable applications.

* > **Pony:** a programming language that uses the Actor model for concurrency and message-passing. Pony is designed to be highly concurrent and provides features like automatic memory management and type safety to make it easier to write reliable and scalable applications.

* > **SwiftActors:** a lightweight Actor model implementation in Swift, designed for building concurrent applications on Apple platforms like iOS, macOS, and watchOS. SwiftActors provides a simple, message-driven programming model for building scalable and responsive applications.

* > **Microsoft Orleans:** a virtual actor model implementation for building distributed systems that scale elastically to handle user load.

* > **Pykka:** a Python implementation of the Actor model that provides a simple and consistent API for building concurrent applications.

* > **Lasp:** a distributed system built on the Actor model that uses CRDTs (Conflict-free Replicated Data Types) to provide a consistent and highly available storage system.

* > **libcppa:** a C++ implementation of the Actor model that provides a flexible and powerful API for building concurrent applications.
