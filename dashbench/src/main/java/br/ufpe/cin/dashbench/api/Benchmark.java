package br.ufpe.cin.dashbench.api;

public enum Benchmark {
    Gson("gson"),
    CommonsMath("commonsmath"),
    Xstream("xstream");

    private final String benchmarkName;

    Benchmark(String benchmarkName) {
        this.benchmarkName=benchmarkName;
    }

    public String toString(){
        return this.benchmarkName;
    }
}
