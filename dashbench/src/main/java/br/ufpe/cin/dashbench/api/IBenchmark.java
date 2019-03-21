package br.ufpe.cin.dashbench.api;

public interface IBenchmark {
    String getName();
    void runWarmUp();
    void runBenchmark();
}
