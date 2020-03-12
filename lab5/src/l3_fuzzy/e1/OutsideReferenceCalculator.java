package l3_fuzzy.e1;

import core.FuzzyPetriLogic.Executor.AsyncronRunnableExecutor;
import core.FuzzyPetriLogic.FuzzyDriver;
import core.FuzzyPetriLogic.FuzzyToken;
import core.FuzzyPetriLogic.PetriNet.FuzzyPetriNet;
import core.FuzzyPetriLogic.PetriNet.Recorders.FullRecorder;
import core.FuzzyPetriLogic.Tables.OneXOneTable;
import core.TableParser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OutsideReferenceCalculator {
    static String reader = "" +
            "{[<NL><NM><ZR><PM><PL>]" +
            " [<NL><NM><ZR><PM><PL>]" +
            " [<NL><NM><ZR><PM><PL>]" +
            " [<NL><NM><ZR><PM><PL>]" +
            " [<NL><NM><ZR><PM><PL>]}";

    static String doubleChannelDifferentiator = ""//
            + "{[<ZR,ZR><NM,NM><NL,NL><NL,NL><NL,NL>]" //
            + " [<PM,PM><ZR,ZR><NM,NM><NL,NL><NL,NL>]" //
            + " [<PL,PL><PM,PM><ZR,ZR><NM,NM><NL,NL>]"//
            + " [<PL,PL><PL,PL><PM,PM><ZR,ZR><NM,NM>]"//
            + " [<PL,PL><PL,PL><PL,PL><PM,PM><ZR,ZR>]}";

    static String t3Table = "{[<FF,ZR>,<FF,FF>, <FF,FF>, <FF,FF>, <ZR, FF>]}";

    private int p1RefInp;
    private FuzzyPetriNet net;
    private FuzzyDriver waterTempDriver;
    private FullRecorder rec;
    private AsyncronRunnableExecutor execcutor;
    //prin constructor primeste perioada referinta pentru plant

    public OutsideReferenceCalculator(HeaterTankControllerComponent htcc, long simPeriod) {
        net = new FuzzyPetriNet();
        TableParser parser = new TableParser();

        int p0 = net.addPlace();
        net.setInitialMarkingForPlace(p0, FuzzyToken.zeroToken());
        p1RefInp = net.addInputPlace();
        int t0 = net.addTransition(0, parser.parseTwoXTwoTable(doubleChannelDifferentiator));
        net.addArcFromPlaceToTransition(p0, t0, 1.0);
        net.addArcFromPlaceToTransition(p1RefInp, t0, 1.0);
        int p2 = net.addPlace();
        net.addArcFromTransitionToPlace(t0, p2);
        int p3 = net.addPlace();
        net.addArcFromTransitionToPlace(t0, p3);
        int t1 = net.addTransition(1, OneXOneTable.defaultTable());
        net.addArcFromPlaceToTransition(p2, t1, 1.0);
        net.addArcFromTransitionToPlace(t1, p0);
        int t2 = net.addOuputTransition(OneXOneTable.defaultTable());
        net.addArcFromPlaceToTransition(p3, t2, 1.0);
        waterTempDriver = FuzzyDriver.createDriverFromMinMax(50, 90);
        net.addActionForOuputTransition(t2, new Consumer<FuzzyToken>() {

            @Override
            public void accept(FuzzyToken tk) {
                //set waterTEmpRef for HTC
                htcc.setWaterRefTemp(waterTempDriver.defuzzify(tk));
            }
        });
//se specifica limitele pt fuzificare
        waterTempDriver = FuzzyDriver.createDriverFromMinMax(-75, 75);
        rec = new FullRecorder();
        //se creaza executorul
        execcutor = new AsyncronRunnableExecutor(net, simPeriod);
        execcutor.setRecorder(rec);
    }
    public void start() {    (new Thread(execcutor)).start();  }

    public void stop() {    execcutor.stop();  }

    public void setInput(double outsideTemp) {
        Map<Integer, FuzzyToken> inps = new HashMap<Integer, FuzzyToken>();
        inps.put(p1RefInp, waterTempDriver.fuzzifie(outsideTemp));
        execcutor.putTokenInInputPlace(inps);
    }
    public FuzzyPetriNet getNet() {    return net;  }

    public FullRecorder getRecorder() {    return rec;  }
}
