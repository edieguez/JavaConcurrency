package com.artemisa.threadpool;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class VegetableChopper implements Runnable {

    @Override
    public void run() {
        log.info("{} is chopping a vegetable", Thread.currentThread().getName());
    }

}
