package com.artemisa.conditionvariable;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
@AllArgsConstructor
public class HungryPerson implements Runnable {

    private static final Lock slowCookerLid = new ReentrantLock();
    private static final Condition soupTaken = slowCookerLid.newCondition();
    private static int servings = 10;

    private final int personId;

    @Override
    public void run() {
        while (servings > 0) {
            slowCookerLid.lock();

            try {
                while ((personId != servings % 2) && servings > 0) {
                    log.info("Person {} checked... then put the lid back", personId);
                    soupTaken.await();
                }

                if (servings > 0) {
                    servings--;
                    log.info("Person {} took some food. Servings left {}", personId, servings);

                    soupTaken.signalAll();
                }
            } catch (InterruptedException e) {
                log.error(e);
            } finally {
                slowCookerLid.unlock();
            }
        }
    }

}
