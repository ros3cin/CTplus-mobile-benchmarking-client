package br.ufpe.cin.androidapplicationsbenchmark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

import br.ufpe.cin.dashbench.api.Benchmark;
import br.ufpe.cin.androidapplicationsbenchmark.benchmarks.BenchmarkFactory;
import br.ufpe.cin.dashbench.api.IBenchmarkRunner;
import br.ufpe.cin.dashbench.api.UI;
import br.ufpe.cin.dashbench.api.VolleyQueue;
import br.ufpe.cin.dashbench.configuration.BenchmarkConfiguration;
import br.ufpe.cin.dashbench.configuration.IpAddress;
import br.ufpe.cin.dashbench.runners.DefaultBenchmarkRunner;

public class MainActivity extends AppCompatActivity {
    private TextView infoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoText = (TextView) findViewById(R.id.info_text);

        UI.getInstance().setInfoTextComponent(this.infoText);
        VolleyQueue.getInstance().setRequestQueue(Volley.newRequestQueue(this));

        UI.getInstance().setInfoText("Application started!");

        String choosenBenchmark = getIntent().getStringExtra("param");
        IpAddress.setEndpointAddress(getIntent());
        BenchmarkConfiguration.getInstance().setIterations(getIntent());

        Benchmark benchmark = choosenBenchmark!=null ? Benchmark.valueOf(choosenBenchmark):null;

        if(benchmark!=null) {
            IBenchmarkRunner benchmarkRunner = new DefaultBenchmarkRunner();
            benchmarkRunner.run(BenchmarkFactory.getInstance().getBenchmark(benchmark));
        } else {
            UI.getInstance().appendInfoText("No benchmark chosen");
        }

    }


}
