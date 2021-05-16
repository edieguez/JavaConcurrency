package com.artemisa;

import com.artemisa.semaphore.Cellphone;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SemaphoreTest {

    @Test
    void semaphoreTest() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Cellphone(String.format("Cellphone %d", i)));
            thread.start();

            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

}
