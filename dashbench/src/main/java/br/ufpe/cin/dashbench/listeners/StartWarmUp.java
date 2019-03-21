package br.ufpe.cin.dashbench.listeners;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import br.ufpe.cin.dashbench.api.EnumDashboard;
import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.UI;
import br.ufpe.cin.dashbench.api.VolleyQueue;

public final class StartWarmUp implements Response.Listener<String> {
    private IBenchmark benchmark;
    public StartWarmUp(IBenchmark benchmark) {
        this.benchmark=benchmark;
    }
    @Override
    public void onResponse(String response) {
        this.benchmark.runWarmUp();
        UI.getInstance().appendInfoText(benchmark.getName()+" warm up finished");
        UI.getInstance().appendInfoText("Starting "+benchmark.getName()+" benchmark");
        StringRequest request = new StringRequest(Request.Method.GET, EnumDashboard.CLEAN_BATTERY.toString(), new WarmUpFinished(benchmark), null);
        VolleyQueue.getInstance().getRequestQueue().add(request);
    }
}
