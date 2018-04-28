package io.micrometer.core.instrument;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

public class MeterRegistryPerfTest {
    private MeterRegistry registry = new SimpleMeterRegistry();

    @Test
    public void test() {

        StopWatch stopWatch = new StopWatch();

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

}
