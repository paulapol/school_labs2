package l1_fuzzy.e2;

import l1_fuzzy.e1.FuzzyDefuzzy;
import l1_fuzzy.e1.FuzzyToken;
import l1_fuzzy.e1.FuzzyValue;
import l1_fuzzy.e1.TwoXTwoTable;

import java.util.EnumMap;
import java.util.Map;

public class SumDifExemple {
    public static TwoXTwoTable createSumDif() {

//construim tabela1 FLRS pentru sumator, prima iesire

        Map<FuzzyValue, Map<FuzzyValue, FuzzyValue>> ruleTable1 = new EnumMap<>(FuzzyValue.class);

        Map<FuzzyValue, FuzzyValue> nlLine = new EnumMap<>(FuzzyValue.class);
        ruleTable1.put(FuzzyValue.NL, nlLine);   //pt linia NL
        nlLine.put(FuzzyValue.NL, FuzzyValue.NL); //si pe coloana NL avem val-> NL
        nlLine.put(FuzzyValue.NM, FuzzyValue.NL); //si pe coloana NM avem val NL
        nlLine.put(FuzzyValue.ZR, FuzzyValue.NL);
        nlLine.put(FuzzyValue.PM, FuzzyValue.NM);
        nlLine.put(FuzzyValue.PL, FuzzyValue.ZR);

        Map<FuzzyValue, FuzzyValue> nmLine = new EnumMap<>(FuzzyValue.class);
        ruleTable1.put(FuzzyValue.NM, nmLine);
        nmLine.put(FuzzyValue.NL, FuzzyValue.NL);
        nmLine.put(FuzzyValue.NM, FuzzyValue.NL);
        nmLine.put(FuzzyValue.ZR, FuzzyValue.NM);
        nmLine.put(FuzzyValue.PM, FuzzyValue.ZR);
        nmLine.put(FuzzyValue.PL, FuzzyValue.PM);

        Map<FuzzyValue, FuzzyValue> zrLine = new EnumMap<>(FuzzyValue.class);
        ruleTable1.put(FuzzyValue.ZR, zrLine);
        zrLine.put(FuzzyValue.NL, FuzzyValue.NL);
        zrLine.put(FuzzyValue.NM, FuzzyValue.NM);
        zrLine.put(FuzzyValue.ZR, FuzzyValue.ZR);
        zrLine.put(FuzzyValue.PM, FuzzyValue.PM);
        zrLine.put(FuzzyValue.PL, FuzzyValue.PL);

        Map<FuzzyValue, FuzzyValue> pmLine = new EnumMap<>(FuzzyValue.class);
        ruleTable1.put(FuzzyValue.PM, pmLine);
        pmLine.put(FuzzyValue.NL, FuzzyValue.NM);
        pmLine.put(FuzzyValue.NM, FuzzyValue.ZR);
        pmLine.put(FuzzyValue.ZR, FuzzyValue.PM);
        pmLine.put(FuzzyValue.PM, FuzzyValue.PL);
        pmLine.put(FuzzyValue.PL, FuzzyValue.PL);

        Map<FuzzyValue, FuzzyValue> plLine = new EnumMap<>(FuzzyValue.class);
        ruleTable1.put(FuzzyValue.PL, plLine);
        plLine.put(FuzzyValue.NL, FuzzyValue.ZR);
        plLine.put(FuzzyValue.NM, FuzzyValue.PM);
        plLine.put(FuzzyValue.ZR, FuzzyValue.PL);
        plLine.put(FuzzyValue.PM, FuzzyValue.PL);
        plLine.put(FuzzyValue.PL, FuzzyValue.PL);

//construim tabela2 FLRS pentru diferentiator, a doua iesire

        Map<FuzzyValue, Map<FuzzyValue, FuzzyValue>> ruleTable2 = new EnumMap<>(FuzzyValue.class);

        Map<FuzzyValue, FuzzyValue> nlLine2 = new EnumMap<>(FuzzyValue.class);
        ruleTable2.put(FuzzyValue.NL, nlLine2);   //pt linia NL
        nlLine2.put(FuzzyValue.NL, FuzzyValue.ZR);  // si coloana NL avem val ZR
        nlLine2.put(FuzzyValue.NM, FuzzyValue.NM);
        nlLine2.put(FuzzyValue.ZR, FuzzyValue.NL);
        nlLine2.put(FuzzyValue.PM, FuzzyValue.NL);
        nlLine2.put(FuzzyValue.PL, FuzzyValue.NL);

        Map<FuzzyValue, FuzzyValue> nmLine2 = new EnumMap<>(FuzzyValue.class);
        ruleTable2.put(FuzzyValue.NM, nmLine2);
        nmLine2.put(FuzzyValue.NL, FuzzyValue.PM);
        nmLine2.put(FuzzyValue.NM, FuzzyValue.ZR);
        nmLine2.put(FuzzyValue.ZR, FuzzyValue.NM);
        nmLine2.put(FuzzyValue.PM, FuzzyValue.NL);
        nmLine2.put(FuzzyValue.PL, FuzzyValue.NL);

        Map<FuzzyValue, FuzzyValue> zrLine2 = new EnumMap<>(FuzzyValue.class);
        ruleTable2.put(FuzzyValue.ZR, zrLine2);
        zrLine2.put(FuzzyValue.NL, FuzzyValue.PL);
        zrLine2.put(FuzzyValue.NM, FuzzyValue.PM);
        zrLine2.put(FuzzyValue.ZR, FuzzyValue.ZR);
        zrLine2.put(FuzzyValue.PM, FuzzyValue.NM);
        zrLine2.put(FuzzyValue.PL, FuzzyValue.NL);

        Map<FuzzyValue, FuzzyValue> pmLine2 = new EnumMap<>(FuzzyValue.class);
        ruleTable2.put(FuzzyValue.PM, pmLine2);
        pmLine2.put(FuzzyValue.NL, FuzzyValue.PL);
        pmLine2.put(FuzzyValue.NM, FuzzyValue.PL);
        pmLine2.put(FuzzyValue.ZR, FuzzyValue.PM);
        pmLine2.put(FuzzyValue.PM, FuzzyValue.ZR);
        pmLine2.put(FuzzyValue.PL, FuzzyValue.NM);

        Map<FuzzyValue, FuzzyValue> plLine2 = new EnumMap<>(FuzzyValue.class);
        ruleTable2.put(FuzzyValue.PL, plLine2);
        plLine2.put(FuzzyValue.NL, FuzzyValue.PL);
        plLine2.put(FuzzyValue.NM, FuzzyValue.PL);
        plLine2.put(FuzzyValue.ZR, FuzzyValue.PL);
        plLine2.put(FuzzyValue.PM, FuzzyValue.PM);
        plLine2.put(FuzzyValue.PL, FuzzyValue.ZR);

//se returneaza tabela FLRS cu doua intrari si doua iesiri
        return new TwoXTwoTable(ruleTable1, ruleTable2);
    }

    public static void main(String[] args) {
        double w1 = 1;
        double w2 = 1;

//se specifica limitele pentru fuzzyficare, defuzzyficare
        FuzzyDefuzzy fuzDefuz =
                new FuzzyDefuzzy(-1.0, -0.5, 0.0, 0.5, 1.0);

//se creează tabela FLRS pentru doua intrari si doua iesiri
        TwoXTwoTable sumdif =  createSumDif();

//se dau cele 2 intari
        double x1 = -0.33;
        double x2 = 0.12;

//se inmultesc intrarile cu factorul de amplificare si se fuzzyfica
        FuzzyToken inpToken1 = fuzDefuz.fuzzyfie(x1 * w1);
        FuzzyToken inpToken2 = fuzDefuz.fuzzyfie(x2 * w2);

//se executa tabela FLRS
        FuzzyToken[] out =sumdif.execute(inpToken1, inpToken2);

//se afiseaza rezultatele defuzificate
        System.out.println("x3 :: " + fuzDefuz.defuzzify(out[0]));
        System.out.println("x4 :: " + fuzDefuz.defuzzify(out[1]));

    }
}
