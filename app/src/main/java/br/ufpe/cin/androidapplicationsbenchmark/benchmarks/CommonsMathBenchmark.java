package br.ufpe.cin.androidapplicationsbenchmark.benchmarks;

import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.stat.regression.GLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.IBenchmarkEndedCallback;
import br.ufpe.cin.dashbench.configuration.BenchmarkConfiguration;

public final class CommonsMathBenchmark implements IBenchmark {
    private IBenchmarkEndedCallback endedCallback;
    public CommonsMathBenchmark(IBenchmarkEndedCallback endedCallback) {
        this.endedCallback=endedCallback;
        setupBenchmark();
    }

    private DescriptiveStatistics descriptiveStatistics;
    private SummaryStatistics summaryStatistics;
    private Frequency frequency;
    private SimpleRegression simpleRegression;
    private OLSMultipleLinearRegression olsMultipleLinearRegression;
    private GLSMultipleLinearRegression glsMultipleLinearRegression;
    private NaturalRanking ranking;
    private Covariance covariance;
    private PearsonsCorrelation pearsonsCorrelation;
    private SpearmansCorrelation spearmansCorrelation;
    private KendallsCorrelation kendallsCorrelation;

    //samples
    private double[] sample1, sample2;

    //regression samples
    private double[][] simpleRegressionSample;
    private double[] rankSample;
    private void setupBenchmark(){
        final int size = 10000;
        sample1 = new double[size];
        sample2 = new double[size];
        simpleRegressionSample = new double[][] { {1,2}, {2,3}, {3,5}, {4,7}, {5,11}, {6,13}, {7,17} };
        double[] multipleRegressionY;
        double[][] multipleRegressionX;
        double[][] omega;
        rankSample = new double[size];

        multipleRegressionY = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
        multipleRegressionX = new double[6][];
        multipleRegressionX[0] = new double[]{0, 0, 0, 0, 0};
        multipleRegressionX[1] = new double[]{2.0, 0, 0, 0, 0};
        multipleRegressionX[2] = new double[]{0, 3.0, 0, 0, 0};
        multipleRegressionX[3] = new double[]{0, 0, 4.0, 0, 0};
        multipleRegressionX[4] = new double[]{0, 0, 0, 5.0, 0};
        multipleRegressionX[5] = new double[]{0, 0, 0, 0, 6.0};
        omega = new double[6][];
        omega[0] = new double[]{1.0, 0, 0, 0, 0, 0};
        omega[1] = new double[]{0, 2.0, 0, 0, 0, 0};
        omega[2] = new double[]{0, 0, 3.0, 0, 0, 0};
        omega[3] = new double[]{0, 0, 0, 4.0, 0, 0};
        omega[4] = new double[]{0, 0, 0, 0, 5.0, 0};
        omega[5] = new double[]{0, 0, 0, 0, 0, 6.0};

        descriptiveStatistics = new DescriptiveStatistics();
        summaryStatistics = new SummaryStatistics();
        frequency = new Frequency();
        simpleRegression = new SimpleRegression();
        olsMultipleLinearRegression = new OLSMultipleLinearRegression();
        glsMultipleLinearRegression = new GLSMultipleLinearRegression();
        ranking = new NaturalRanking(NaNStrategy.MINIMAL, TiesStrategy.MAXIMUM);
        covariance = new Covariance();
        pearsonsCorrelation = new PearsonsCorrelation();
        spearmansCorrelation = new SpearmansCorrelation();
        kendallsCorrelation = new KendallsCorrelation();

        olsMultipleLinearRegression.newSampleData(multipleRegressionY,multipleRegressionX);



        glsMultipleLinearRegression.newSampleData(multipleRegressionY,multipleRegressionX,omega);

        simpleRegression.addData(simpleRegressionSample);
        for(int i = 0; i < size; i++) {
            descriptiveStatistics.addValue(i);
            summaryStatistics.addValue(i);
            sample1[i]=(double)i;
            sample2[i]=(double)i+1;
            frequency.addValue((double)i);
            rankSample[i]=(double)i;
        }
    }
    private void callAPIMethods(){
        if(descriptiveStatistics !=null){
            descriptiveStatistics.getGeometricMean();
            descriptiveStatistics.getKurtosis();
            descriptiveStatistics.getMax();
            descriptiveStatistics.getMean();
            descriptiveStatistics.getMin();
            descriptiveStatistics.getN();
            descriptiveStatistics.getPercentile(0.5);
            descriptiveStatistics.getPopulationVariance();
            descriptiveStatistics.getSkewness();
            descriptiveStatistics.getStandardDeviation();
            descriptiveStatistics.getSum();
            descriptiveStatistics.getSumsq();
            descriptiveStatistics.getVariance();
            descriptiveStatistics.getWindowSize();
        }
        if(summaryStatistics != null){
            summaryStatistics.getGeometricMean();
            summaryStatistics.getMax();
            summaryStatistics.getMean();
            summaryStatistics.getMin();
            summaryStatistics.getN();
            summaryStatistics.getPopulationVariance();
            summaryStatistics.getStandardDeviation();
            summaryStatistics.getSum();
            summaryStatistics.getSumsq();
            summaryStatistics.getVariance();
            summaryStatistics.getSumOfLogs();
            summaryStatistics.getSecondMoment();
        }
        if( (sample1 !=null) && (sample1.length>0) ) {
            StatUtils.geometricMean(sample1);
            StatUtils.max(sample1);
            StatUtils.mean(sample1);
            StatUtils.meanDifference(sample1, sample2);
            StatUtils.min(sample1);
            StatUtils.mode(sample1);
            StatUtils.normalize(sample2);
            StatUtils.percentile(sample1,0.5);
            StatUtils.populationVariance(sample1);
            StatUtils.product(sample1);
            StatUtils.sum(sample1);
            StatUtils.sumDifference(sample1,sample2);
            StatUtils.sumLog(sample1);
            StatUtils.sumSq(sample1);
            StatUtils.variance(sample1);
            StatUtils.varianceDifference(sample1,sample2,StatUtils.meanDifference(sample1,sample2));
        }
        if(frequency!=null) {
            frequency.getCount((double)2);
            frequency.getCumFreq((double)0);
            frequency.getCumPct((double)0);
            frequency.getPct((double)5);
            frequency.getSumFreq();
            frequency.getUniqueCount();
        }
        if( (simpleRegression!=null) ) {
            simpleRegression.getIntercept();
            simpleRegression.getInterceptStdErr();
            simpleRegression.getMeanSquareError();
            simpleRegression.getN();
            simpleRegression.getR();
            simpleRegression.getRegressionSumSquares();
            simpleRegression.getRSquare();
            simpleRegression.getSignificance();
            simpleRegression.getSlope();
            simpleRegression.getSlopeConfidenceInterval();
            simpleRegression.getSlopeStdErr();
            simpleRegression.getSumOfCrossProducts();
            simpleRegression.getTotalSumSquares();
            simpleRegression.getXSumSquares();
            simpleRegression.getSumSquaredErrors();
        }
        if (olsMultipleLinearRegression != null){
            olsMultipleLinearRegression.estimateErrorVariance();
            olsMultipleLinearRegression.estimateRegressandVariance();
            olsMultipleLinearRegression.estimateRegressionParameters();
            olsMultipleLinearRegression.estimateRegressionParametersStandardErrors();
            olsMultipleLinearRegression.estimateRegressionParametersVariance();
            olsMultipleLinearRegression.estimateRegressionStandardError();
            olsMultipleLinearRegression.estimateResiduals();
            olsMultipleLinearRegression.calculateAdjustedRSquared();
            olsMultipleLinearRegression.calculateHat();
            olsMultipleLinearRegression.calculateResidualSumOfSquares();
            olsMultipleLinearRegression.calculateRSquared();
            olsMultipleLinearRegression.calculateTotalSumOfSquares();
        }
        if(glsMultipleLinearRegression != null){
            glsMultipleLinearRegression.estimateErrorVariance();
            glsMultipleLinearRegression.estimateRegressandVariance();
            glsMultipleLinearRegression.estimateRegressionParameters();
            glsMultipleLinearRegression.estimateRegressionParametersStandardErrors();
            glsMultipleLinearRegression.estimateRegressionParametersVariance();
            glsMultipleLinearRegression.estimateRegressionStandardError();
            glsMultipleLinearRegression.estimateResiduals();
        }
        if(ranking != null) {
            ranking.rank(rankSample);
        }
        covariance = new Covariance();
        pearsonsCorrelation = new PearsonsCorrelation();
        spearmansCorrelation = new SpearmansCorrelation();
        kendallsCorrelation = new KendallsCorrelation();
        if(covariance!=null){
            covariance.covariance(sample1,sample2);
            covariance.covariance(sample1,sample2,false);
        }
        if(pearsonsCorrelation!=null){
            pearsonsCorrelation.correlation(sample1,sample2);
        }
        if(spearmansCorrelation!=null) {
            spearmansCorrelation.correlation(sample1,sample2);
        }
        if(kendallsCorrelation!=null){
            spearmansCorrelation.correlation(sample1,sample2);
        }
        TestUtils.t(sample1,sample2);
        TestUtils.kolmogorovSmirnovTest(sample1,sample2);
        TestUtils.homoscedasticT(sample1,sample2);
        TestUtils.pairedT(sample1,sample2);
        TestUtils.pairedTTest(sample1,sample2);
        TestUtils.tTest(sample1,sample2);
        TestUtils.kolmogorovSmirnovStatistic(sample1,sample2);
        TestUtils.homoscedasticTTest(sample1,sample2);
    }

    @Override
    public String getName(){
        return "Commons Math";
    }

    @Override
    public void runWarmUp() {
        for(int i = 0; i < BenchmarkConfiguration.getInstance().getNumberOfWarmUpIterations(); i++){
            callAPIMethods();
        }

    }

    @Override
    public void runBenchmark(){
        for(int i =0; i < BenchmarkConfiguration.getInstance().getNumberOfIterations(); i++){
            callAPIMethods();
        }
        if(endedCallback!=null) {
            endedCallback.execute();
        }
    }
}
