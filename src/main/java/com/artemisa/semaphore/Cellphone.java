package com.artemisa.semaphore;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@AllArgsConstructor
public class Cellphone implements Runnable {

    private static Semaphore charger = new Semaphore(4);

    private String name;

    @Override
    public void run() {
        try {
            charger.acquire();
            log.info("{} is charging...", name);

            Thread.sleep(ThreadLocalRandom.current().nextInt(1_000, 2_000));
        } catch (InterruptedException e) {
            log.error(e);
        } finally {
            charger.release();
            log.info("{} is done charging!", name);
        }
    }

}
