/**
 * Copyright 2017 Pivotal Software, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
