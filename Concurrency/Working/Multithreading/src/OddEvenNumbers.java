
public class OddEvenNumbers {


    public static void main(String[] args) {
        Object mutex = new Object();

        Thread evenThread = new Thread(new NumberThread(mutex,2,5));
        evenThread.setPriority(1);
        Thread oddThread = new Thread(new NumberThread(mutex,1,5));
        evenThread.setPriority(2);

        oddThread.start();
        evenThread.start();

    }


}

class NumberThread implements Runnable{

    final Object mutex;
    int start;
    int end;
    NumberThread(Object mutex,int start,int end){
        this.mutex = mutex;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        synchronized (mutex){
            while(start<=end) {
                System.out.println(start + " Thread :: " + Thread.currentThread().getName());
                start += 2;
                mutex.notifyAll();
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            mutex.notifyAll();
        }
    }

}
