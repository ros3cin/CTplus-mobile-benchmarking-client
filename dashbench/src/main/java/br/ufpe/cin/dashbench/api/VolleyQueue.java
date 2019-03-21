package br.ufpe.cin.dashbench.api;

import com.android.volley.RequestQueue;

public class VolleyQueue {
    private static final VolleyQueue ourInstance = new VolleyQueue();
    private RequestQueue queue;
    public static VolleyQueue getInstance() {
        return ourInstance;
    }

    private VolleyQueue() {
    }

    public void setRequestQueue(RequestQueue queue){
        this.queue=queue;
    }

    public RequestQueue getRequestQueue() {
        return this.queue;
    }
}
