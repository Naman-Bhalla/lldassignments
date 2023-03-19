Implement a distributed cache that supports following requirements:
1. Read your own writes
2. Multiple write policies: 
   1. write back
   2. write through
3. Multiple eviction algorithms
    1. LRU
    2. LFU
4. Async processing of requests for getting, putting, and deleting data
5. Request Collapsing for get requests for better performance
6. Prefetching data for better performance