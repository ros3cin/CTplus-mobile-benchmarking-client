package br.ufpe.cin.androidapplicationsbenchmark.benchmarks;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.dashbench.api.IBenchmark;
import br.ufpe.cin.dashbench.api.IBenchmarkEndedCallback;
import br.ufpe.cin.androidapplicationsbenchmark.benchmarks.xstream.artifacts.XstreamClassOne;
import br.ufpe.cin.dashbench.configuration.BenchmarkConfiguration;

public final class XstreamBenchmark implements IBenchmark {
    private IBenchmarkEndedCallback endedCallback;
    public XstreamBenchmark(IBenchmarkEndedCallback endedCallback) {
        this.endedCallback=endedCallback;
        setupBenchmark();
    }

    //for the benchmark
    private XstreamClassOne classOne;
    private String classOneXmlString;
    private XStream xstream;
    private void setupBenchmark() {
        classOne = new XstreamClassOne();
        classOne.setField1("Field 1");
        classOne.setField2(2);
        classOne.setField3(3.1f);
        classOne.setField4(4.1);
        classOne.setField5(5L);

        List<String> field6 = new ArrayList<String>();
        for(int i = 0; i < 100; i++) {
            field6.add(String.valueOf(i));
        }
        classOne.setField6(field6);

        List<Integer> field7 = new ArrayList<Integer>();
        for(int i = 0; i < 100; i++) {
            field7.add(i);
        }
        classOne.setField7(field7);

        xstream = new XStream(new DomDriver());
        xstream.allowTypes(XstreamClassOne.class);
        classOneXmlString = xstream.toXML(classOne);
    }

    @Override
    public String getName(){
        return "Xstream";
    }

    @Override
    public void runWarmUp() {
        for(int i = 0; i < BenchmarkConfiguration.getInstance().getNumberOfWarmUpIterations(); i++){
            xstream.toXML(classOne);
            xstream.fromXML(classOneXmlString);
        }

    }

    @Override
    public void runBenchmark(){
        for(int i = 0; i < BenchmarkConfiguration.getInstance().getNumberOfIterations(); i++){
            xstream.toXML(classOne);
            xstream.fromXML(classOneXmlString);
        }

        if(endedCallback!=null) {
            endedCallback.execute();
        }
    }
}
