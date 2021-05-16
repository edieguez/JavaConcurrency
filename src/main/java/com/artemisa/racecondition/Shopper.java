package com.artemisa.racecondition;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
@AllArgsConstructor
public class Shopper implements Runnable {

    public static int bagsOfChips = 1;
    private static final Lock pencil = new ReentrantLock();
    private static final CyclicBarrier barrier = new CyclicBarrier(10);

    private final String name;

    @Override
    public void run() {
        if (this.name.contains("Olivia")) {
            pencil.lock();

            try {
                bagsOfChips += 3;
                log.info("{} ADDED three bags of chips", name);
            } finally {
                pencil.unlock();
            }

            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                log.error(e);
            }
        } else {
            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                log.error(e);
            }

            pencil.lock();

            try {
                bagsOfChips *= 2;
                log.info("{} DOUBLED bags of chips", name);
            } finally {
                pencil.unlock();
            }
        }
    }
}
