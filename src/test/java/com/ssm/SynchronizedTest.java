package com.ssm;

import org.junit.Test;

/**
 * @Author: Think
 * @Date: 2019/4/16
 * @Time: 18:30
 */
public class SynchronizedTest extends BaseTest {

    private byte[] lock = new byte[0];

    @Test
    public void test() {
        synchronized (lock) {

        }
    }

    class inner implements Runnable {

        @Override
        public void run() {

        }
    }
}

