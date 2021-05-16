package com.artemisa.producerconsumer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.BlockingQueue;

@Log4j2
@AllArgsConstructor
public class SoupProducer implements Runnable {

    private final BlockingQueue<String> servingLine;

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                while (!servingLine.offer(String.format("Bowl #%s", i))) {
                    log.info("Serving line full. Retrying...");
                    Thread.sleep(210);
                }

                log.info("Served bowl #{}. Remaining capacity is {}", i, servingLine.remainingCapacity());

                Thread.sleep(210);
            } catch (InterruptedException e) {
                log.info(e);
            }
        }
    }

}
