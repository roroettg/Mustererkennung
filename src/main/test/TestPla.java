import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import mustererkennung.algorithmen.InputHelper;
import mustererkennung.algorithmen.Merkmal;
import mustererkennung.algorithmen.PLA;
import mustererkennung.algorithmen.Pocket;

import org.junit.Test;

public class TestPla {

	private String[] bewegung = { "gehen", "sitzen", "treppe", "joggen", "drehen" };

	public double getMerkmal(Merkmal m) {
		return m.getRangeAccX()[0]; // ok,ok,23
		// return m.getRangeAccZ()[0]; //ok, ,
		// return m.getMaxAccX()[1]; // ,ok,
		// return m.getMaxAccY()[1]; // 5,ok, 5
		// return m.getAverageAccY()[2]; // 4, 4, 2
		// return m.getAverageAccX()[2]; // 2, 2, 1
	}

	public double[] getMerkmalA(Merkmal m) {
		double[] merk = new double[3];
		merk[0] = m.getAverageAccX()[0];
		merk[1] = m.getRangeAccX()[0];
		merk[2] = m.getMaxAccX()[1];
		return merk;
	}

	@Test
	public void test_2Klassen_1Neuron() {
		InputHelper helper = new InputHelper();
		ArrayList<Merkmal> merkmale = helper.getLernDaten(bewegung[0]);
		merkmale.addAll(helper.getLernDaten(bewegung[1]));
		double[][] werte = new double[merkmale.size()][1];
		double[][] result = new double[merkmale.size()][1];
		double max = 0;
		int i = 0;
		for (Merkmal m : merkmale) {
			werte[i][0] = this.getMerkmal(m);
			result[i][0] = (merkmale.get(i).getBewegungsart() == bewegung[0] ? 1 : 0);
			i++;
			if (Math.abs(max) < Math.abs(this.getMerkmal(m))) {
				max = this.getMerkmal(m);
			}
		}
		for (i = 0; i < werte.length && max != 0; i++) {
			werte[i][0] = werte[i][0] / max;
		}
		PLA p = new PLA(1, 1);
		p.train(werte, result);

		// Testen des lernens
		ArrayList<Merkmal> mV = helper.getVerificationDaten(bewegung[0]);
		mV.addAll(helper.getVerificationDaten(bewegung[1]));
		double[][] verify = new double[mV.size()][1];
		i = 0;
		for (Merkmal m : mV) {
			verify[i++][0] = this.getMerkmal(m) / max;
		}
		int fehler = 0;
		for (i = 0; i < mV.size(); i++) {
			double loesung = (mV.get(i).getBewegungsart() == bewegung[0] ? 1 : 0);
			if (loesung != p.getF().toDiskret(p.fire(verify[i])[0])) {
				System.out.println("Fehler: ");
				System.out.println(mV.get(i).getBewegungsart() + this.getMerkmal(mV.get(i)));
				System.out.println(loesung + " " + p.getF().toDiskret(p.fire(verify[i])[0]) + " Soll: " + mV.get(i).getBewegungsart());
				fehler++;
			}
		}
		System.out.println("Fehler: " + fehler + " von " + mV.size());
		assertTrue(fehler <= mV.size() * 0.25);
	}

	@Test
	public void test_2Klassen_2Neuronen() {
		InputHelper helper = new InputHelper();
		ArrayList<Merkmal> merkmale = helper.getLernDaten(bewegung[0]);
		merkmale.addAll(helper.getLernDaten(bewegung[1]));
		double[][] werte = new double[merkmale.size()][1];
		double[][] result = new double[merkmale.size()][2];
		double max = 0;
		int i = 0;
		for (Merkmal m : merkmale) {
			werte[i][0] = this.getMerkmal(m);
			result[i][0] = (merkmale.get(i).getBewegungsart() == bewegung[0] ? 1 : 0);
			result[i][1] = (merkmale.get(i).getBewegungsart() == bewegung[1] ? 1 : 0);
			i++;
			if (Math.abs(max) < Math.abs(this.getMerkmal(m))) {
				max = this.getMerkmal(m);
			}
		}
		for (i = 0; i < werte.length && max != 0; i++) {
			werte[i][0] = werte[i][0] / max;
		}
		PLA p = new PLA(2, 1);
		p.train(werte, result);

		// Testen des lernens
		ArrayList<Merkmal> mV = helper.getVerificationDaten(bewegung[0]);
		mV.addAll(helper.getVerificationDaten(bewegung[1]));
		double[][] verify = new double[mV.size()][1];
		i = 0;
		for (Merkmal m : mV) {
			verify[i++][0] = this.getMerkmal(m) / max;
		}
		int fehler = 0;
		for (i = 0; i < mV.size(); i++) {
			double[] loesung = new double[2];
			loesung[0] = (mV.get(i).getBewegungsart() == bewegung[0] ? 1 : 0);
			loesung[1] = (mV.get(i).getBewegungsart() == bewegung[1] ? 1 : 0);
			if (loesung[0] != p.getF().toDiskret(p.fire(verify[i])[0]) || loesung[1] != p.getF().toDiskret(p.fire(verify[i])[1])) {
				System.out.println("Fehler: ");
				System.out.println(mV.get(i).getBewegungsart() + this.getMerkmal(mV.get(i)));
				System.out.println(loesung[0] + " " + p.getF().toDiskret(p.fire(verify[i])[0]) + " Soll: " + mV.get(i).getBewegungsart());
				System.out.println(loesung[1] + " " + p.getF().toDiskret(p.fire(verify[i])[1]) + " Soll: " + mV.get(i).getBewegungsart());
				fehler++;
			}
		}
		System.out.println("Fehler: " + fehler + " von " + mV.size());
		assertTrue(fehler <= mV.size() * 0.25);
	}

	@Test
	public void test_3Klassen_3Neuronen() {
		InputHelper helper = new InputHelper();
		int anzKlassen = 3;
		int anzMerkmale = 3;
		ArrayList<Merkmal> merkmale = new ArrayList<Merkmal>();
		ArrayList<Merkmal> mV = new ArrayList<Merkmal>();
		for (int i = 0; i < anzKlassen; i++) {
			merkmale.addAll(helper.getLernDaten(bewegung[i]));
			mV.addAll(helper.getVerificationDaten(bewegung[i]));
		}
		double[][] werte = new double[merkmale.size()][anzMerkmale];
		double[][] result = new double[merkmale.size()][anzKlassen];
		double[][] verify;
		verify = new double[mV.size()][anzMerkmale];
		double[] max = new double[anzMerkmale];
		int i = 0;
		for (Merkmal m : merkmale) {
			for (int j = 0; j < anzMerkmale; j++) {
				werte[i][j] = this.getMerkmalA(m)[j];
			}
			for (int j = 0; j < anzKlassen; j++) {
				result[i][j] = (merkmale.get(i).getBewegungsart() == bewegung[j] ? 1 : 0);
			}
			i++;
			int k = 0;
			for (double d : this.getMerkmalA(m)) {
				if (Math.abs(max[k]) < Math.abs(d)) {
					max[k] = d;
				}
			}
		}
		for (int a = 0; a < anzMerkmale; a++) {
			for (i = 0; i < werte.length && max[a] != 0; i++) {
				werte[i][a] = werte[i][a] / max[a];
			}
		}
		PLA p = new PLA(anzKlassen, anzMerkmale);
		p.train(werte, result);
		// Testen des lernens
		i = 0;
		for (Merkmal m1 : mV) {
			for (int a = 0; a < anzMerkmale; a++) {
				verify[i][a] = this.getMerkmalA(m1)[a] / max[a];
			}
			i++;
		}
		int fehler = 0;
		for (i = 0; i < mV.size(); i++) {
			double[] loesung = new double[anzKlassen];
			for (int j = 0; j < anzKlassen; j++) {
				loesung[j] = (mV.get(i).getBewegungsart() == bewegung[j] ? 1 : 0);
			}
			for (int j = 0; j < anzKlassen; j++) {
				if (loesung[j] != p.getF().toDiskret(p.fire(verify[i])[j])) {
					fehler++;
					StringBuffer b = new StringBuffer("Fehler " + bewegung[j] + " ");
					for (int a = 0; a < anzKlassen; a++) {
						b.append(loesung[a] + " =" + p.getF().toDiskret(p.fire(verify[i])[a]) + "; ");
					}
					System.out.println(b.toString());
					break;
				}
			}
		}
		System.out.println("Fehler: " + fehler + " von " + mV.size());
		assertTrue(fehler <= mV.size() * 0.25);
	}
}
