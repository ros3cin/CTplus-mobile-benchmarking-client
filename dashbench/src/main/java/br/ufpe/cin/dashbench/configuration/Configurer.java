package br.ufpe.cin.dashbench.configuration;

import android.content.Context;
import android.content.Intent;

import com.android.volley.toolbox.Volley;

import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.IBenchmarkRunner;
import br.ufpe.cin.dashbench.api.VolleyQueue;
import br.ufpe.cin.dashbench.runners.DefaultBenchmarkRunner;

public class Configurer {
    private static final Configurer ourInstance = new Configurer();

    public static Configurer getInstance() {
        return ourInstance;
    }

    private Configurer() {
    }

    public void configureAndRun(Intent mainActivity, Context activityContext, IBenchmark benchmark) {
        String benchParam = mainActivity.getStringExtra("param");
        if((benchParam!=null) && !benchParam.isEmpty()) {
            VolleyQueue.getInstance().setRequestQueue(Volley.newRequestQueue(activityContext));
            IpAddress.setEndpointAddress(mainActivity);
            BenchmarkConfiguration.getInstance().setIterations(mainActivity);
            IBenchmarkRunner benchmarkRunner = new DefaultBenchmarkRunner();
            benchmarkRunner.run(benchmark);
        }
    }
}
