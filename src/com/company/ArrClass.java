package com.company;

public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;

    public ArrClass(int dim, int threadNum) {
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;
        for(int i = 0; i < dim; i++){
            arr[i] = i + 1;
        }
    }

    public long partSum(int startIndex, int finishIndex){
        long sum = 0;
        for(int i = startIndex; i < finishIndex; i++){
            sum += arr[i];
        }
        return sum;
    }

    private long sum = 0;

    synchronized private long getSum() {
        while (getThreadCount() < threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    synchronized public void collectSum(long sum){
        this.sum += sum;
    }

    private int threadCount = 0;
    synchronized public void incThreadCount(){
        threadCount++;
        if(threadCount >= threadNum) {
            notifyAll();
        }
    }

    private int getThreadCount() {
        return threadCount;
    }

    public long threadSum(){
        ThreadSum[] threadSums = new ThreadSum[threadNum];


        int quarter = dim / 4;
        threadSums[0] = new ThreadSum(0, quarter, this);
        threadSums[1] = new ThreadSum(quarter, quarter * 2, this);
        threadSums[2] = new ThreadSum(quarter * 2, quarter * 3, this);
        threadSums[3] = new ThreadSum(quarter * 3, dim, this);


        for(int i = 0; i < threadNum; i++){
            threadSums[i].start();
        }

        return getSum();
    }
}