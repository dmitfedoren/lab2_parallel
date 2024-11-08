package com.company;

public class Main {

    public static void main(String[] args) {
        int dim = 10000000; // Розмір масиву
        int threadNum = 4;  // Кількість потоків

        ArrClass arrClass = new ArrClass(dim, threadNum);

        long sequentialSum = arrClass.partSum(0, dim);
        System.out.println("Послідовна сума: " + sequentialSum);

        long parallelSum = arrClass.threadSum();
        System.out.println("Паралельна сума: " + parallelSum);
        
        if(sequentialSum == parallelSum) {
            System.out.println("Суми збігаються.");
        } else {
            System.out.println("Суми не збігаються.");
        }
    }
}