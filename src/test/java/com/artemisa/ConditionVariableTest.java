package com.artemisa;

import com.artemisa.conditionvariable.HungryPerson;
import org.junit.jupiter.api.Test;

public class ConditionVariableTest {

    @Test
    void conditionVariableTest() throws InterruptedException {
        Thread threadA = new Thread(new HungryPerson(0));
        Thread threadB = new Thread(new HungryPerson(1));
        Thread threadC = new Thread(new HungryPerson(2));
        Thread threadD = new Thread(new HungryPerson(3));
        Thread threadE = new Thread(new HungryPerson(4));

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
        threadE.join();
    }

}
