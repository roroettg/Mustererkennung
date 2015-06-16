import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.algorithmen.PLA;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestPla {

	private String bewegung1 = "treppe";
	private String bewegung2 = "sitzen";
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
			werte[i][0] = m.getAverageAccX()[0];
			result[i] = (merkmale.get(i).getBewegungsart() == bewegung1 ? 1 : 0);
			i++;
			if (Math.abs(max) < Math.abs(m.getAverageAccX()[0])) {
				max = m.getAverageAccX()[0];
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
			verify[i++][0] = m.getAverageAccX()[0] / max;
			System.out.println(m.getBewegungsart() + m.getAverageAccX()[0]);
		}
		for (i = 0; i < mV.size(); i++) {
			double loesung = (mV.get(i).getBewegungsart() == bewegung1 ? 1 : 0);
			System.out.println(loesung + " " + p.getF().toDiskret(p.fire(verify[i])) + " Soll: " + mV.get(i).getBewegungsart());
			assertTrue(loesung == p.getF().toDiskret(p.fire(verify[i])));
		}
	}

}
