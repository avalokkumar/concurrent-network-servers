> It is obvious that a lot of compute resources are wasted while the server processes a client connection. Even assuming a client that sends messages immediately and doesn't do any waiting, network communication is still involved; networks tend to be millions (or more) times slower than a modern CPU, so the CPU running the sequential server will spend the vast majority of time in glorious boredom waiting for new socket traffic to arrive.


### Some drawbacks of the sequential server implementation:

* **Limited concurrency:** Since the server can handle only one client at a time, the throughput of the server is limited. If multiple clients try to connect to the server simultaneously, some of them will have to wait until the server finishes processing the current client.

* **Inefficient resource usage:** When the server is waiting for a client to complete, it is not doing anything useful. This wastes valuable resources such as CPU time and memory.

* **Increased latency:** Because the server can only process one request at a time, the latency (i.e. the time it takes for a client to receive a response) may be higher than in other concurrency models.

* **Scalability issues:** The sequential server may not scale well as the number of clients grows. It is difficult to handle a large number of concurrent clients efficiently using a sequential model.

* **Limited fault tolerance:** If the server crashes or hangs while processing a client request, it may leave the client in an undefined state, which can be problematic for certain applications.
