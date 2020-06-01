import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Sorter {
    private  int nThreads;
    private AtomicInteger curThreads;

    public Sorter(){
        nThreads = 1;
        curThreads = new AtomicInteger(1);
    }
    public void sort(int[] array){
       mergeSort(array);
    }
    public void setN(int n){
        if(n < 1){
            nThreads = 1;
            return;
        }
        nThreads = n;
    }
    private void mergeSort(int[] array){
        if(array.length <= 1 ){
            return;
        }
        Thread threadA = null;
        int[] part1 = Arrays.copyOfRange(array, 0, array.length/2);
        int[] part2 = Arrays.copyOfRange(array, array.length/2, array.length);
        Boolean hasThreadA = false;
    int tCount = curThreads.getAndIncrement();
        if(tCount <= nThreads){
        threadA = new Thread(() -> {
            mergeSort(part1);

        });
        threadA.start();
        hasThreadA = true;
    }else{
        curThreads.decrementAndGet();
        mergeSort(part1);
    }
    mergeSort(part2);
        if(hasThreadA){
        try {
            threadA.join();
        } catch (InterruptedException e) {

        }
        curThreads.decrementAndGet();
    }

        merge(part1, part2, array);
}
     private void merge(int[] arrayA, int[] arrayB, int[]result){
        int i = 0;
        int i1 = 0;
        int i2 = 0;


         while(i1 < arrayA.length && i2 < arrayB.length){
             if(arrayA[i1] < arrayB[i2]){
                 result[i] = arrayA[i1];
                 i++;
                 i1++;
             }else{
                 result[i] = arrayB[i2];
                 i++;
                 i2++;
             }
         }
         while(i1 < arrayA.length) {
             result[i] = arrayA[i1];
             i++;
             i1++;
         }
         while(i2 < arrayB.length ){
             result[i] = arrayB[i2];
             i++;
             i2++;
         }

     }

}
