package br.ufpe.cin.dashbench.listeners;

import com.android.volley.Response;

import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.UI;

public final class WarmUpFinished implements Response.Listener<String> {
    private IBenchmark benchmark;
    public WarmUpFinished(IBenchmark benchmark) {
        this.benchmark=benchmark;
    }
    @Override
    public void onResponse(String response) {
        this.benchmark.runBenchmark();
        UI.getInstance().appendInfoText("Benchmark "+benchmark.getName()+" finished");
    }
}
