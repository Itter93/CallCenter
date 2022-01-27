import java.util.concurrent.ArrayBlockingQueue;

public class Calling extends Thread {

    private ArrayBlockingQueue<String> queue;
    private int call = 1000;
    private int working = 4000;
    private int callsCount;

    public Calling(int callsCount) {
        this.callsCount = callsCount;
        this.queue = new ArrayBlockingQueue<>(callsCount);
    }


    public void call() {
        for (int i = 1; i <= callsCount; i++) {
            queue.add(Thread.currentThread().getName() + i);
            try {
                Thread.sleep(call);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Входящий вызов от " + Thread.currentThread().getName() + i);
        }
    }

    public void takeCall() {
        int contSecond = 0;
        while (!isInterrupted()) {
            if (queue.size() != 0) {
                String client = null;
                try {
                    client = queue.take();
                    Thread.sleep(working);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Вызов принял " + Thread.currentThread().getName() + " от клиента " + client);
            } else {
                try {

                    Thread.sleep(call);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contSecond += call;
                if (contSecond == working) return;
            }
        }

    }

}
