package pla;

import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// TODO: Auto-generated Javadoc
/**
 * The Class InputHelper.
 */
public class InputHelper {

	/** The sensoren. */
	String[][] sensoren = { { "sensor0", "01324189" },
			{ "sensor1", "01324188" }, { "sensor2", "01324180" } };

	/** The value tags. */
	String[] valueTags = { "averageAccX", "averageAccY", "averageAccZ",
			"stdAccX", "stdAccY", "stdAccZ", "medianAccX", "medianAccY",
			"medianAccZ", "rangeAccX", "rangeAccY", "rangeAccZ", "maxAccX",
			"maxAccY", "maxAccZ", "minAccX", "minAccY", "minAccZ" };

	/** The files_gehen. */
	private final String[] files_gehen_lern = {
			"LernDaten/02/gehen.json",
			"Lerndaten/07/gehen.json",
			"Lerndaten/08/gehen.json",
			"Lerndaten/13/gehen.json",
			"Lerndaten/15/gehen.json",
			"Lerndaten/16/gehen.json",
			"Lerndaten/20/gehen.json", };

	/** The files_sitzen. */
	private final String[] files_sitzen_lern = {
			"Lerndaten/02/sitzen.json",
			"Lerndaten/07/sitzen.json",
			"Lerndaten/08/sitzen.json",
			"Lerndaten/13/sitzen.json",
			"Lerndaten/15/sitzen.json",
			"Lerndaten/16/sitzen.json",
			"Lerndaten/20/sitzen.json" };

	private final String[] files_joggen_lern = {
			"Lerndaten/02/joggen.json",
			"Lerndaten/07/joggen.json",
			"Lerndaten/08/laufen.json",
			"Lerndaten/13/laufen.json",
			"Lerndaten/15/joggen.json",
			"Lerndaten/16/joggen.json",
			"Lerndaten/20/laufen.json" };

	private final String[] files_treppe_lern = {
			"Lerndaten/02/treppe.json",
			"Lerndaten/07/treppe.json",
			"Lerndaten/08/treppe.json",
			"Lerndaten/13/treppe.json",
			"Lerndaten/15/treppe.json",
			"Lerndaten/16/treppe.json",
			"Lerndaten/20/treppe.json" };

	/** The files_gehen. */
	private final String[] files_gehen_test = {
			"TestDaten/03/gehen3.json",
			"TestDaten/04/gehen4.json",
			"TestDaten/05/gehen5.json",
			"TestDaten/06/gehen6.json",
			"TestDaten/09/gehen9.json" };
	/** The files_gehen. */

	private final String[] files_sitzen_test = {
			"TestDaten/03/sitzen3.json",
			"TestDaten/04/sitzen4.json",
			"TestDaten/05/sitzen5.json",
			"TestDaten/06/sitzen6.json",
			"TestDaten/09/sitzen9.json" };

	private final String[] files_joggen_test = {
			"TestDaten/03/joggen3.json",
			"TestDaten/04/joggen4.json",
			"TestDaten/05/joggen5.json",
			"TestDaten/06/joggen6.json",
			"TestDaten/09/joggen9.json" };


	private final String[] files_treppe_test = {
			"TestDaten/03/treppe3.json",
			"TestDaten/04/treppe4.json",
			"TestDaten/05/treppe5.json",
			"TestDaten/06/treppe6.json",
			"TestDaten/09/treppe9.json" };
	/**
	 * Gets the lern daten.
	 *
	 * @param _sensor
	 *            the _sensor
	 * @return the lern daten
	 */
	public ArrayList<Merkmal> getLernDaten(String bewegung) {
		ArrayList<Merkmal> m = new ArrayList<Merkmal>();
		String[] file = null;
		switch (bewegung) {
		case "joggen":
			file = files_joggen_lern;
			break;
		case "sitzen":
			file = files_sitzen_lern;
			break;
		case "gehen":
			file = files_gehen_lern;
			break;
		case "treppe":
			file = files_treppe_lern;
			break;
		}
		if (file != null) {
			for (String s : file) {
				m.addAll(this.getValues(s, bewegung));
			}
		}
		return m;
	}

	/**
	 * Gets the verification daten.
	 *
	 * @param _sensor
	 *            the _sensor
	 * @return the verification daten
	 */
	public ArrayList<Merkmal> getVerificationDaten(String bewegung) {
		ArrayList<Merkmal> m = new ArrayList<Merkmal>();
		String[] file = files_joggen_lern;
		switch (bewegung) {
		case "joggen":
			file = files_joggen_test;
			break;
		case "sitzen":
			file = files_sitzen_test;
			break;
		case "gehen":
			file = files_gehen_test;
			break;
		case "treppe":
			file = files_treppe_test;
			break;
		}
		if (file != null) {
			for (String s : file) {
				m.addAll(this.getValues(s, bewegung));
			}
		}
		return m;
	}

	/**
	 * Gets the values.
	 *
	 * @param strID
	 *            the str id
	 * @param strSensor
	 *            the str sensor
	 * @param file
	 *            the file
	 * @param bewegung
	 *            the bewegung
	 * @return the values
	 */
	public ArrayList<Merkmal> getValues(String file, String bewegung) {
		ArrayList<Merkmal> merkmale = new ArrayList<Merkmal>();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray[] sensorArray = new JSONArray[3];
			sensorArray[0] = (JSONArray) ((JSONObject) jsonObject
					.get(sensoren[0][0])).get(sensoren[0][1]);
			sensorArray[1] = (JSONArray) ((JSONObject) jsonObject
					.get(sensoren[1][0])).get(sensoren[1][1]);
			sensorArray[2] = (JSONArray) ((JSONObject) jsonObject
					.get(sensoren[2][0])).get(sensoren[2][1]);
			Merkmal m = new Merkmal("", bewegung);
			for (int i = 0; i < sensorArray[0].size(); i++) {
				double[] d = new double[sensoren.length];
				for (int e = 0; e < sensoren.length; e++) {
					d[e] = (Double) ((JSONObject) sensorArray[e].get(i))
							.get(this.valueTags[i]);
					m.setByTag(this.valueTags[i], d);
				}
			}
			merkmale.add(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merkmale;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String[] path = { "LernDaten/02/gehen.json" };
		new InputHelper().getValues(path[0], "gehen");
	}

}
