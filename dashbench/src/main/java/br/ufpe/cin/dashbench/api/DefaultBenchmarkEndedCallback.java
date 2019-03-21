package br.ufpe.cin.dashbench.api;

import br.ufpe.cin.dashbench.api.EnumDashboard;
import br.ufpe.cin.dashbench.api.IBenchmarkEndedCallback;
import br.ufpe.cin.dashbench.api.UI;
import br.ufpe.cin.dashbench.api.VolleyQueue;
import br.ufpe.cin.dashbench.listeners.DefaultErrorListener;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public final class DefaultBenchmarkEndedCallback implements IBenchmarkEndedCallback {
    @Override
    public void execute() {
        UI.getInstance().appendInfoText("Benchmark finished!");
        StringRequest request = new StringRequest(Request.Method.GET, EnumDashboard.DONE.toString(),null,new DefaultErrorListener());
        VolleyQueue.getInstance().getRequestQueue().add(request);
    }
}
