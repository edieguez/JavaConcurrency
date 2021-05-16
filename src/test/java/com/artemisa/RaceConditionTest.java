package com.artemisa;

import com.artemisa.racecondition.Shopper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class RaceConditionTest {

    @Test
    void raceConditionTest() throws InterruptedException {
        Thread[] shoppers = new Thread[10];

        for (int i = 0; i < shoppers.length / 2; i++) {
            shoppers[i * 2] = new Thread(new Shopper(String.format("Barron %d", i)));
            shoppers[i * 2 + 1] = new Thread(new Shopper(String.format("Olivia %d", i)));

            shoppers[i * 2].start();
            shoppers[i * 2 + 1].start();
        }

        for (Thread shopper : shoppers) {
            shopper.join();
        }

        log.info("We need to buy {} bags of chips", Shopper.bagsOfChips);
    }

}
