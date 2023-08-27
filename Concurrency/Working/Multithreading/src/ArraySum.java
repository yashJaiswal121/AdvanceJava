import java.util.concurrent.atomic.AtomicInteger;
//Calculate the sum of array by divinding and calculating answer in chunks, where each chunk is calculated by one thread.
//Each chunk should process similar Number of elements
public class ArraySum {

    volatile AtomicInteger noOfThreadsExecutionCompleted = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
       new ArraySum().execute();
    }

    public void execute() throws InterruptedException {
        int[] arr = new int[100];
        for(int i=1;i<=100;i++){
            arr[i-1] = i;
        }

        int chunks=5;
        int chunksCpy=5;

        int chunk_size = arr.length/chunks;
        int[] chunks_answer = new int[chunks];
        int chunk_start = 1;
        int chunk_end = chunk_size;

        while (chunksCpy-- > 0){
            //Not Making class ElementsProcessor run() Thread safe, even after a shared variable chunks_answer
            Thread thread = new Thread(new ElementsProcessor(arr,chunks_answer,chunk_start-1,chunk_end-1,chunksCpy,noOfThreadsExecutionCompleted));
            thread.start();
            chunk_start = chunk_end+1;
            chunk_end+=chunk_size;
        }


        while(noOfThreadsExecutionCompleted.get() < chunks){
            Thread.sleep(10);
        }

        //Now all answers are computed
        int sum=0;
        for(int i=0;i<chunks_answer.length;i++){
            sum+=chunks_answer[i];
        }

        System.out.println("Sum from :"+ Thread.currentThread().getName() + " is = "+sum);

    }

}

class ElementsProcessor implements Runnable{
    private int start;
    private int end;
    private int[] arr;
    private int[] chunkAnsArr;
    private int chunkNo;
    private volatile AtomicInteger noOfThreadsExecutionCompleted;

    public ElementsProcessor(int[] arr, int[] chunkAnsArr, int start, int end, int chunkNo, AtomicInteger noOfThreadsExecutionCompleted){
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.chunkAnsArr = chunkAnsArr;
        this.chunkNo = chunkNo;
        this.noOfThreadsExecutionCompleted = noOfThreadsExecutionCompleted;
    }


    @Override
    public void run() {
        int sum=0;
        for(int i=start;i<=end;i++){
            sum+=arr[i];
        }

        //System.out.println("Sum from :"+ Thread.currentThread().getName() + " is = "+sum);
        chunkAnsArr[chunkNo] = sum;
        noOfThreadsExecutionCompleted.incrementAndGet();
    }
}
