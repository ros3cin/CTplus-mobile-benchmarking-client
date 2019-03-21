package br.ufpe.cin.androidapplicationsbenchmark.benchmarks;

import br.ufpe.cin.dashbench.api.DefaultBenchmarkEndedCallback;
import br.ufpe.cin.dashbench.api.Benchmark;
import br.ufpe.cin.dashbench.api.IBenchmark;

public class BenchmarkFactory {
    private static final BenchmarkFactory ourInstance = new BenchmarkFactory();

    public static BenchmarkFactory getInstance() {
        return ourInstance;
    }

    private BenchmarkFactory() {
    }

    public IBenchmark getBenchmark(Benchmark benchmark){
        switch (benchmark) {
            case Gson:
                return new GsonBenchmark(new DefaultBenchmarkEndedCallback());
            case CommonsMath:
                return new CommonsMathBenchmark(new DefaultBenchmarkEndedCallback());
            case Xstream:
                return new XstreamBenchmark(new DefaultBenchmarkEndedCallback());
                default:
                    return new GsonBenchmark(new DefaultBenchmarkEndedCallback());

        }
    }
}
