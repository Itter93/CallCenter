public class Main {
    public static int calls = 30;
    public static int operators = 3;

    public static void main(String[] args) {

        Calling calling = new Calling(calls);

        new Thread(null, calling::call, "Клиент № ").start();

        for (int i = 1; i <= operators; i++) {
            new Thread(null, calling::takeCall, "Оператор № " + i).start();
        }

    }
}


