package com.artemisa.producerconsumer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.BlockingQueue;

@Log4j2
@AllArgsConstructor
public class SoupConsumer implements Runnable {

    private final BlockingQueue<String> servingLine;

    @Override
    public void run() {
        while (!servingLine.isEmpty()) {
            try {
                String bowl = servingLine.poll();

                if (bowl != null) {
                    log.info("Ate {}", bowl);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                log.info(e);
            }
        }
    }

}
