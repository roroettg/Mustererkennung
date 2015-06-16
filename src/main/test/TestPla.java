import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.algorithmen.PLA;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestPla {

	private String bewegung1 = "gehen";
	private String bewegung2 = "sitzen";
	
	public double getMerkmal(Merkmal m){
		return m.getRangeAccY()[0];     //ok,ok,23
		//return m.getRangeAccZ()[0];	//ok,  ,
		//return m.getMaxAccX()[1];     //  ,ok,
		//return m.getMaxAccY()[1];     // 5,ok, 5
		//return m.getAverageAccY()[2]; // 4, 4, 2
		//return m.getAverageAccX()[2]; // 2, 2, 1 
	}
	
	@Test
	public void test() {
		InputHelper helper = new InputHelper();
		ArrayList<Merkmal> merkmale = helper.getLernDaten(bewegung1);
		merkmale.addAll(helper.getLernDaten(bewegung2));
		double[][] werte = new double[merkmale.size()][1];
		double[] result = new double[merkmale.size()];
		double max = 0;
		int i = 0;
		for (Merkmal m : merkmale) {
			werte[i][0] = this.getMerkmal(m);
			result[i] = (merkmale.get(i).getBewegungsart() == bewegung1 ? 1 : 0);
			i++;
			if (Math.abs(max) < Math.abs(this.getMerkmal(m))) {
				max = this.getMerkmal(m);
			}
		}
		for (i = 0; i < werte.length && max != 0; i++) {
			werte[i][0] = werte[i][0] / max;
		}
		PLA p = new PLA(1);
		p.train(werte, result);

		// Testen des lernens
		ArrayList<Merkmal> mV = helper.getVerificationDaten(bewegung1);
		mV.addAll(helper.getVerificationDaten(bewegung2));
		double[][] verify = new double[mV.size()][1];
		i = 0;
		for (Merkmal m : mV) {
			verify[i++][0] = this.getMerkmal(m) / max;
		}
		int fehler =0;
		for (i = 0; i < mV.size(); i++) {
			double loesung = (mV.get(i).getBewegungsart() == bewegung1 ? 1 : 0);
			if(loesung != p.getF().toDiskret(p.fire(verify[i]))){
				System.out.println("Fehler: ");
				System.out.println(mV.get(i).getBewegungsart() + this.getMerkmal(mV.get(i)));
				System.out.println(loesung + " " + p.getF().toDiskret(p.fire(verify[i])) + " Soll: " + mV.get(i).getBewegungsart());
				
				fehler++;
			}
		}
		assertTrue(fehler == 0);

	}
}
