package com.ming.concurrency.example.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 7:30 PM 2019/4/29
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer>{

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0 ;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute){
            for (int i = start ; i <= end ; i++){
                sum += i;
            }
        }else {
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start,middle);
            ForkJoinTaskExample right = new ForkJoinTaskExample(middle+1 , end);
            leftTask.fork();
            right.fork();
            int leftResult = leftTask.join();
            int rightReult = right.join();
            sum = leftResult + rightReult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务
        ForkJoinTaskExample taskExample = new ForkJoinTaskExample(1,100);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(taskExample);
        try {
            log.info("result:{}",result.get());
        }catch (Exception e){
            log.error("exception",e);
        }
    }
}
