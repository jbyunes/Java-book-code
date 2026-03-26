package chapter.generic.constructor;


public class Computation {
    private Runnable task;
    public <T> Computation(Producer<? extends T> supplier, Consumer<? super T> consumer) {
        task = new Runnable() {
            @Override
            public void run() {
                consumer.consume(supplier.produce());
            }
        };
    }
    public Computation(Runnable task) { this.task = task; }
    public void compute() { task.run(); }
 
}