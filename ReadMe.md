## a simple server side rate limiter
* In this simple project I control number of calls to getBooks API via rate limiter
* I assumed it is a single node server. In a distributed architecture, 
   we need to think about other solutions, for example a redis cluster that all pods have access to
* To keep number of hits so far for a user, I used a concurrentMap to make it work in multi thread environment
* I could use synchronization, but in high multi-thread apps it would be inefficient