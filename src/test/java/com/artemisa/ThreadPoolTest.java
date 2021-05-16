package com.artemisa;

import com.artemisa.threadpool.VegetableChopper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Log4j2
public class ThreadPoolTest {

    @Test
    void threadPoolTest() throws InterruptedException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        log.info("Scheduling tasks using {} processors", availableProcessors);

        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Thread(new VegetableChopper()));
        }

        executorService.shutdown();

        while (!executorService.awaitTermination(1, TimeUnit.MILLISECONDS)) {
            log.info("There are still pending tasks. Waiting...");
        }

        log.info("Finished all the tasks");
    }

}
