package io.micrometer.core.instrument;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

public class MeterRegistryPerfTest {

    private MeterRegistry registry = new SimpleMeterRegistry();

    @Test
    public void test20000() {

        StopWatch stopWatch = new StopWatch("20000");

        // worming
        for (int i = 0; i < 1000; i++) {
            registry.counter("my.counter", "w" + i, "v1");
        }

        stopWatch.start();
        for (int i = 0; i < 20000; i++) {
            registry.counter("my.counter", "k" + i, "v1");
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void test100000() {

        StopWatch stopWatch = new StopWatch("100000");

        // worming
        for (int i = 0; i < 1000; i++) {
            registry.counter("my.counter", "w" + i, "v1");
        }

        stopWatch.start();
        for (int i = 0; i < 100000; i++) {
            registry.counter("my.counter", "k" + i, "v1");
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testGet() {

        StopWatch stopWatch = new StopWatch("getMeters 20000");

        for (int i = 0; i < 20000; i++) {
            registry.counter("my.counter", "k" + i, "v1");
        }

        stopWatch.start();
        registry.getMeters();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testGetOne() {

        StopWatch stopWatch = new StopWatch("get one - k5000");

        for (int i = 0; i < 20000; i++) {
            registry.counter("my.counter", "k" + i, "v1");
        }

        stopWatch.start();
        registry.get("k5000");
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
