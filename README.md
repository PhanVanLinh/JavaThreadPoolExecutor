# JavaThreadPoolExecutor

### Example

    int corePollSize = 6;
    int maximumPoolSize = 10;
    int keepAliveTime = 8;
    int maximumPendingTask = 20;
    BlockingQueue<Runnable> queues = new ArrayBlockingQueue<>(maximumPendingTask);
    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePollSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queues);

=> This code create a `Executor`
- Have 6 available threads (always live)
- Can create more 4 extras thread if needed (because maximum threads is 10). And this "4 extras threads" will alive in 8 seconds(`keepAliveTime`) after it idle
- Can handling maximum 30 tasks at a time (10 tasks will execute by 10 threads (6 available threads and 4 extras threads), 20 tasks will put to queue) . If you put 31 tasks to `Executor`  at a time, it will throw exception.

After `Executor` execute all tasks, it still have 6 available threads (always live), so we should call `executor.shutdown()` to release it.

### Note

`ThreadPoolExecutor` **extends** `AbstractExecutorService`  **implements** `ExecutorService ` **extends** `Executor`.
A Future represents the result of an asynchronous computation (quite similar to callback)

ThreadPool keep thread alive by using `while (task != null)` inside run methods :D

### Using
Example, we want to download 100 images from internet, we can create 100 threads to do this, however it is not good for some device which have low performance
We can use `ThreadPoolExecutor` to specific the number of thread uses (example we can use 2, 3, ... threads for download images at a time)