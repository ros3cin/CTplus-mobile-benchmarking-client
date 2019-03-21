package br.ufpe.cin.dashbench.listeners;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public final class DefaultErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        System.out.println(error.getMessage());
    }
}
