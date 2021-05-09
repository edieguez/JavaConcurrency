package com.artemisa;

import com.artemisa.producerconsumer.SoupConsumer;
import com.artemisa.producerconsumer.SoupProducer;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
public class ProducerConsumerTest {

    @Test
    void producerConsumerTest() throws InterruptedException {
        BlockingQueue<String> servingLine = new ArrayBlockingQueue<>(5);
        Thread soupProducer = new Thread(new SoupProducer(servingLine));
        Thread soupConsumer = new Thread(new SoupConsumer(servingLine));

        soupProducer.start();

        Thread.sleep(100); // Wait a bit before starting consumer. Workaround for race condition

        soupConsumer.start();

        soupProducer.join();
        soupConsumer.join();
    }

}
