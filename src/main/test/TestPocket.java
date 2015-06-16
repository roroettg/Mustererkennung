import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.algorithmen.Pocket;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestPocket {

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
		double[][] result = new double[merkmale.size()][1];
		double max = 0;
		int i = 0;
		for (Merkmal m : merkmale) {
			werte[i][0] = this.getMerkmal(m);
			result[i][0] = (merkmale.get(i).getBewegungsart() == bewegung1 ? 1 : 0);
			i++;
			System.out.println(m.getBewegungsart() + this.getMerkmal(m));
			if (Math.abs(max) < Math.abs(m.getAverageAccX()[0])) {
				max = this.getMerkmal(m);
			}
		}
		for (i = 0; i < werte.length && max != 0; i++) {
			werte[i][0] = werte[i][0] / max;
		}

		Pocket p = new Pocket(1);
		System.out.println("Fertig nach " + p.train(werte, result) + " Iterationen");

		// Testen des lernens
		ArrayList<Merkmal> mV = helper.getVerificationDaten(bewegung1);
		mV.addAll(helper.getVerificationDaten(bewegung2));
		double[][] verify = new double[mV.size()][1];
		i = 0;
		for (Merkmal m : mV) {
			verify[i++][0] = this.getMerkmal(m) / max;
			System.out.println(m.getBewegungsart() + this.getMerkmal(m));
		}
		int fehler =0;
		for (i = 0; i < mV.size(); i++) {
			double loesung = (mV.get(i).getBewegungsart() == bewegung1 ? 1 : 0);
			System.out.println(loesung + " " + p.getF().toDiskret(p.fire(verify[i])[0]) + " Soll: " + mV.get(i).getBewegungsart());
			if(loesung != p.getF().toDiskret(p.fire(verify[i])[0])){
				fehler++;
			}
		}
		assertTrue(fehler == 0);
	}

}
