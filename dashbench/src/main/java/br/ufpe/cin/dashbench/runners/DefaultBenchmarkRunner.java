package br.ufpe.cin.dashbench.runners;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import br.ufpe.cin.dashbench.api.EnumDashboard;
import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.IBenchmarkRunner;
import br.ufpe.cin.dashbench.api.UI;
import br.ufpe.cin.dashbench.api.VolleyQueue;
import br.ufpe.cin.dashbench.listeners.DefaultErrorListener;
import br.ufpe.cin.dashbench.listeners.StartWarmUp;

public final class DefaultBenchmarkRunner implements IBenchmarkRunner {
    @Override
    public void run(IBenchmark benchmark) {
        UI.getInstance().appendInfoText("Starting warm up");
        StringRequest request = new StringRequest(Request.Method.GET, EnumDashboard.CLEAN_BATTERY.toString(), new StartWarmUp(benchmark), new DefaultErrorListener());
        VolleyQueue.getInstance().getRequestQueue().add(request);
    }
}
