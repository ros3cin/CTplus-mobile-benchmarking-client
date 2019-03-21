package br.ufpe.cin.androidapplicationsbenchmark.benchmarks;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.IBenchmarkEndedCallback;
import br.ufpe.cin.androidapplicationsbenchmark.benchmarks.gson.artifacts.GsonClassOne;
import br.ufpe.cin.dashbench.configuration.BenchmarkConfiguration;

public final class GsonBenchmark implements IBenchmark {
    private IBenchmarkEndedCallback endedCallback;
    public GsonBenchmark(IBenchmarkEndedCallback endedCallback) {
        this.endedCallback=endedCallback;
        setupBenchmark();
    }

    //for the benchmark
    private GsonClassOne classOne;
    private String classOneJson;

    private void setupBenchmark(){
        //setting up class for benchmark
        classOne = new GsonClassOne();
        classOne.setV1(1);
        classOne.setV2(2L);
        classOne.setV3("Benchmarked");
        classOne.setV4(3.5f);
        classOne.setV5(3.6);

        List<Integer> v6 = new ArrayList<Integer>();
        for(int i = 0; i < 100;i++){
            v6.add(i);
        }
        classOne.setV6(v6);

        List<String> v7 = new ArrayList<String>();
        for(int i = 0; i < 100;i++){
            v7.add(String.valueOf(i));
        }
        classOne.setV7(v7);

        Gson gson = new Gson();
        classOneJson = gson.toJson(classOne);
    }

    @Override
    public String getName(){
        return "Gson";
    }

    @Override
    public void runWarmUp() {
        Gson gson = new Gson();
        for(int i = 0; i < BenchmarkConfiguration.getInstance().getNumberOfWarmUpIterations(); i++){
            gson.toJson(classOne);
            gson.fromJson(classOneJson,GsonClassOne.class);
        }
    }

    @Override
    public void runBenchmark(){
        Gson gson = new Gson();
        for(int i = 0; i < BenchmarkConfiguration.getInstance().getNumberOfIterations(); i++){
            gson.toJson(classOne);
            gson.fromJson(classOneJson,GsonClassOne.class);
        }
        if(endedCallback!=null) {
            endedCallback.execute();
        }
    }
}
