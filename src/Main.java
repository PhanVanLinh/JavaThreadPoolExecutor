import java.util.concurrent.*;

public class Main {

    static class AThread extends Thread {
        private String name;

        AThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("run " + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run finished " + name);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello, World");

        BlockingQueue<Runnable> queues = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, queues);
        for (int i = 0; i < 15; i++) {
            executor.execute(new AThread("" + i));
        }
        executor.shutdown(); // shut down to make executor release all available thread
    }
}
