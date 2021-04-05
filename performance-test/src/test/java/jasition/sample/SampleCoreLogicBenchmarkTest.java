package jasition.sample;

import jasition.sample.SampleCoreLogic;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

public class SampleCoreLogicBenchmarkTest {
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void doNothing() {
        int a = (int) (Math.random() * 10);
        int b = (int) (Math.random() * 10);

        SampleTestFixturesKt.sample().add(a, b);
    }
}
