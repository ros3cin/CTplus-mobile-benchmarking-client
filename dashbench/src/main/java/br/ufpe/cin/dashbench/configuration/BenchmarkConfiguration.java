package br.ufpe.cin.dashbench.configuration;

import android.content.Intent;

public class BenchmarkConfiguration {
    private static final BenchmarkConfiguration ourInstance = new BenchmarkConfiguration();
    private int numberOfWarmUpIterations = 10;
    private int numberOfIterations = 100;

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public static BenchmarkConfiguration getInstance() {
        return ourInstance;
    }

    private BenchmarkConfiguration() {
    }

    public int getNumberOfWarmUpIterations(){
        return numberOfWarmUpIterations;
    }
    public void setNumberOfWarmUpIterations(int numberOfWarmUpIterations) {
        this.numberOfWarmUpIterations = numberOfWarmUpIterations;
    }

    /**
     * Expects the main activity to have been initialized with the
     * following extra parameters:
     * - nWarmUpIterations the number of warm iterations
     * - nIterations the number of real iterations
     * @param mainActivityIntent
     */
    public void setIterations(Intent mainActivityIntent) {
        String nWarmUpIterations = mainActivityIntent.getStringExtra("nWarmUpIterations");
        String nIterations = mainActivityIntent.getStringExtra("nIterations");
        if( (nWarmUpIterations!=null) && (!"".equals(nWarmUpIterations)) ){
            this.setNumberOfWarmUpIterations(Integer.parseInt(nWarmUpIterations));
        }
        if( (nIterations!=null) && (!"".equals(nIterations)) ){
            this.setNumberOfIterations(Integer.parseInt(nIterations));
        }
    }
}
