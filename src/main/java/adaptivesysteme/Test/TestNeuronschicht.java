package adaptivesysteme.Test;

import adaptivesysteme.NeuronNetz.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestNeuronschicht {

	int max_iterationen = 1000000;
	
	@Test
	public void testAND() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };
		Transferfunktion f = new TransferExp();
		NeuronenSchicht schicht = new NeuronenSchicht(2, 1, f);
		int i;
		boolean fehler = true;
		for (i = 1; i < max_iterationen && fehler; i++) {
			if(i % 1000 == 0){
				f.increaseLambda();
			}
			for (int j = 0; j < training.length; j++) {
				schicht.train(training[j], loesung_AND[j]);
			}
			fehler = false;
			for (int j = 0; j < training.length; j++) {
				if (f.toDiskret(schicht.fire(training[j])[0]) != loesung_AND[j][0]) {
					fehler = true;
					break;
				}
			}
		}
		System.out.println("Fertig nach " + i + " Iterationen");
		schicht.print_W();
		for (int j = 0; j < training.length; j++) {
			System.out.println(training[j][0] + " AND " + training[j][0] + " = " + f.toDiskret(schicht.fire(training[j])[0]));
			assertTrue(f.toDiskret(schicht.fire(training[j])[0]) == loesung_AND[j][0]);
		}
	}

	
	@Test
	public void testAND_Tanh() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 0 }, { 0 }, { 1 } };
		Transferfunktion f = new TransferModiefiedTanh();
		NeuronenSchicht schicht = new NeuronenSchicht(2, 1, f);
		int i;
		boolean fehler = true;
		for (i = 0; i < max_iterationen && fehler; i++) {
			for (int j = 0; j < training.length; j++) {
				schicht.train(training[j], loesung_AND[j]);
			}
			fehler = false;
			for (int j = 0; j < training.length; j++) {
				if (f.toDiskret(schicht.fire(training[j])[0]) != loesung_AND[j][0]) {
					fehler = true;
					break;
				}
			}
		}
		System.out.println("Fertig nach " + i + " Iterationen");
		schicht.print_W();
		for (int j = 0; j < training.length; j++) {
			System.out.println(training[j][0] + " AND " + training[j][0] + " = " + f.toDiskret(schicht.fire(training[j])[0]));
			assertTrue(f.toDiskret(schicht.fire(training[j])[0]) == loesung_AND[j][0]);
		}
	}
	
	
	@Test
	public void testOR() {
		double[][] training = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[][] loesung_AND = { { 0 }, { 1 }, { 1 }, { 1 } };
		Transferfunktion f = new TransferExp();
		NeuronenSchicht schicht = new NeuronenSchicht(2, 1, f);
		int i;
		boolean fehler = true;
		for (i = 1; i < max_iterationen && fehler; i++) {
			if(i % 1000 == 0){
				f.increaseLambda();
			}
			for (int j = 0; j < training.length; j++) {
				schicht.train(training[j], loesung_AND[j]);
			}
			fehler = false;
			for (int j = 0; j < training.length; j++) {
				if (f.toDiskret(schicht.fire(training[j])[0]) != loesung_AND[j][0]) {
					fehler = true;
					break;
				}
			}
		}
		System.out.println("Fertig nach " + i + " Iterationen");
		schicht.print_W();
		for (int j = 0; j < training.length; j++) {
			System.out.println(training[j][0] + " AND " + training[j][0] + " = " + f.toDiskret(schicht.fire(training[j])[0]));
			assertTrue(f.toDiskret(schicht.fire(training[j])[0]) == loesung_AND[j][0]);
		}
	}
	
	
	@Test
	public void testTrain() {
		Transferfunktion f = new TransferStufe();
		NeuronenSchicht schicht = train(f);
		assertNotNull(schicht);
	}
	

	@Test
	public void testEinheitskreisFehlerrate() throws Exception {
		Transferfunktion f = new TransferExp();
		NeuronenSchicht schicht = train(f);
		System.out.println("Die Gewichte nach Training");
		schicht.print_W();
		// Test ist positiv falls weniger als 10 % fehlerrate
		assertTrue(verify(schicht, true, 1, f) < 10);
	}

	@Test
	public void testTrainEinheisvektoren() throws Exception {
		Transferfunktion f = new TransferExp();
		NeuronenSchicht schicht = new NeuronenSchicht(3, 3, f);
		ArrayList<double[][]> values = new ArrayList<double[][]>();
		double[][] set = new double[2][3];
		set[0][0] = -1;
		set[0][1] = 0;
		set[0][2] = 0;
		set[1][0] = 0;
		set[1][1] = 0;
		set[1][2] = 0;
		values.add(set);
		set = new double[2][3];
		set[0][0] = 0;
		set[0][1] = -1;
		set[0][2] = 0;
		set[1][0] = 0;
		set[1][1] = 0;
		set[1][2] = 0;
		values.add(set);
		set = new double[2][3];
		set[0][0] = 0;
		set[0][1] = 0;
		set[0][2] = -1;
		set[1][0] = 0;
		set[1][1] = 0;
		set[1][2] = 0;
		values.add(set);
		set = new double[2][3];
		set[0][0] = 1;
		set[0][1] = 0;
		set[0][2] = 0;
		set[1][0] = 1;
		set[1][1] = 0;
		set[1][2] = 0;
		values.add(set);
		set = new double[2][3];
		set[0][0] = 0;
		set[0][1] = 1;
		set[0][2] = 0;
		set[1][0] = 0;
		set[1][1] = 1;
		set[1][2] = 0;
		values.add(set);
		set = new double[2][3];
		set[0][0] = 0;
		set[0][1] = 0;
		set[0][2] = 1;
		set[1][0] = 0;
		set[1][1] = 0;
		set[1][2] = 1;
		values.add(set);

		for (int i = 0; i < max_iterationen; i++) {
			if(i % 1000 == 0){
				f.increaseLambda();
			}
			for (double[][] s : values) {
				schicht.train(s[0], s[1]);
			}
		}
		schicht.print_W();
		assertTrue(verify(schicht, false, 1, f) <= 10);
	}

	@Test
	public void testVerifyUnnormiert() throws Exception {
		Transferfunktion f = new TransferModiefiedTanh();
		NeuronenSchicht schicht = train(f);
		schicht.print_W();
		assertTrue(verify(schicht, false, 10, f) <= 10);
	}

	@Test
	public void testEinheitskreisMinimal() throws Exception {
		Transferfunktion f = new TransferStufe();
		NeuronenSchicht schicht = train(f);
		double[] v = new double[3];
		v[0] = 0;
		v[1] = 0;
		v[2] = 1;
		schicht.print_W();
		assertTrue(schicht.fire(v)[0] == 0);
		assertTrue(schicht.fire(v)[1] == 0);
		assertTrue(schicht.fire(v)[2] == 1);
		v[0] = 0;
		v[1] = 1;
		v[2] = 0;
		assertTrue(schicht.fire(v)[0] == 0);
		assertTrue(schicht.fire(v)[1] == 1);
		assertTrue(schicht.fire(v)[2] == 0);
		v[0] = 1;
		v[1] = 0;
		v[2] = 0;
		assertTrue(schicht.fire(v)[0] == 1);
		assertTrue(schicht.fire(v)[1] == 0);
		assertTrue(schicht.fire(v)[2] == 0);
	}

	/**
	 * Hilfsfunktion zum trainiren der Neuronenschicht
	 * 
	 * */
	private NeuronenSchicht train(Transferfunktion f) {
		NeuronenSchicht schicht = new NeuronenSchicht(3, 3, f);
		ArrayList<double[][]> values = new ArrayList<double[][]>();
		for (int i = 0; i < 100; i++) {
			values.add(erzeugeVector(true, 1));
		}
		int fehler = 1, i;
		// Trainiere die Neuronenschicht, solange bis alle Trainingsvektoren
		// erfolgreich erkannt werden oder Iterationen erreicht wurde
		for (i = 0; fehler != 0 && i < max_iterationen; i++) {
			fehler = 0;
			if(i % 1000 == 0){
				f.increaseLambda();
			}
			for (double[][] set : values) {
				double[] y = schicht.train(set[0], set[1]);
				if (f.toDiskret(y[0]) != set[1][0] || f.toDiskret(y[1]) != set[1][1] || f.toDiskret(y[2]) != set[1][2]) {
					fehler++;
				}
			}
		}
		System.out.println("Trainingsdurchläufe Durchläufe : " + i + " Trainingsfehler: " + fehler);
		return schicht;
	}

	/**
	 * Hilfsfunktion die die Einheitsvektoren erzeugt und die Erwartungswerte
	 * bestimmt
	 * 
	 * @param norm
	 *            Soll der Vektor auf Einheitsvektor verlänger/verkürzt werden
	 * @param offset
	 *            Der maximale Wert der vom Vektor erreicht werden soll
	 * */
	private double[][] erzeugeVector(boolean norm, double offset) {
		double[][] set = new double[2][3];
		// Erzeuge Vektor mit Random Werten
		set[0][0] = ((Math.random() * (2 * offset)) - offset); // X richtung
		set[0][1] = ((Math.random() * (2 * offset)) - offset); // Y richtung
		set[0][2] = ((Math.random() * (2 * offset)) - offset); // Z richtung

		// Falls der Vektor ein Einheitsvektor sein soll, wird die Länge des
		// Vektors angepasst
		if (norm) {
			double v = (Math.pow(set[0][0], 2) + Math.pow(set[0][1], 2) + Math.pow(set[0][2], 2));
			set[0][0] /= v;
			set[0][1] /= v;
			set[0][2] /= v;
		}
		// Festlegen der Erwatungswerte anhand der erzeugten Vektoren
		set[1][0] = set[0][0] > 0 ? 1 : 0;
		set[1][1] = set[0][1] > 0 ? 1 : 0;
		set[1][2] = set[0][2] > 0 ? 1 : 0;
		// Sollen die Vektoren auf den Wertebereich [0:1] angepasst werden
		if (norm) {
			set[0][0] = (set[0][0] + 1) / 2;
			set[0][1] = (set[0][1] + 1) / 2;
			set[0][2] = (set[0][2] + 1) / 2;
		} else {
			// Falls nicht, Vektoren jeweils um 0.5 verschieben, damit 0 der
			// gleiche Wert ist wie bei den Angelernten Werten
			set[0][0] += 0.5;
			set[0][1] += 0.5;
			set[0][2] += 0.5;
		}
		return set;
	}

	/**
	 * Hilfsfunktion um die Ergebnisse des Trainings zu überprüfen
	 *
	 * @param schicht
	 *            the schicht
	 * @return gibt die Anzahl der nicht richtig erkannten Vektoren zurück
	 */
	public int verify(NeuronenSchicht schicht, boolean norm, int offset, Transferfunktion f) {
		ArrayList<double[][]> values = new ArrayList<double[][]>();
		int setSize = 100;
		int fehler = 0;
		for (int i = 0; i < setSize; i++) {
			values.add(erzeugeVector(norm, offset));
		}
		for (double[][] set : values) {
			double[] result = schicht.fire(set[0]);
			if (set[1][0] != f.toDiskret(result[0]) || set[1][1] != f.toDiskret(result[1]) || set[1][2] != f.toDiskret(result[2])) {
				fehler++;
				StringBuffer b = new StringBuffer("Fehler: ");
				for (int i = 0; i < 3; i++) {
					b.append(set[0][i] + ", ");
				}
				b.append("Result:");
				for (int i = 0; i < 3; i++) {
					b.append(result[i] + ", ");
				}
				System.out.println(b.toString());
			}
		}
		System.out.println("Fehlerrate: " + fehler + " von " + setSize);
		return fehler;
	}

}
