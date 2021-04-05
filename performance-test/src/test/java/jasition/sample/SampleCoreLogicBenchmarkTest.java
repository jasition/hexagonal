package jasition.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

public class SampleCoreLogicBenchmarkTest {
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 3, time = 3)
    @Fork(2)
    public void doNothing() {
        int a = (int) (Math.random() * 10);
        int b = (int) (Math.random() * 10);

        SampleTestFixturesKt.sample().add(a, b);
    }
}
