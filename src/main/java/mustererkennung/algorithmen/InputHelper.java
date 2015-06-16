package mustererkennung.algorithmen;

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
	String[][] sensoren = { { "sensor0", "01324189" }, { "sensor1", "01324188" }, { "sensor2", "01324180" } };

	/** The value tags. */
	String[] valueTags = { "avgAccXWithDelta", "avgAccYWithDelta", "avgAccZWithDelta", "stdAccXWithDelta", "stdAccYWithDelta", "stdAccZWithDelta",
			"medianAccXWithDelta", "medianAccYWithDelta", "medianAccZWithDelta", "rangeAccXWithDelta", "rangeAccYWithDelta", "rangeAccZWithDelta",
			"maxAccXWithDelta", "maxAccYWithDelta", "maxAccZWithDelta", "minAccXWithDelta", "minAccYWithDelta", "minAccZWithDelta" };

	/** The files_gehen. */
	private final String[] files_gehen_lern = { "LernDaten/02/gehen.json", "LernDaten/07/gehen.json", "LernDaten/08/gehen.json", "LernDaten/13/gehen.json",
			"LernDaten/15/gehen.json", "LernDaten/16/gehen.json", "LernDaten/20/gehen.json", };

	/** The files_sitzen. */
	private final String[] files_sitzen_lern = { "LernDaten/02/sitzen.json", "LernDaten/07/sitzen.json", "LernDaten/08/sitzen.json",
			"LernDaten/13/sitzen.json", "LernDaten/15/sitzen.json", "LernDaten/16/sitzen.json", "LernDaten/20/sitzen.json" };

	private final String[] files_joggen_lern = { "LernDaten/02/joggen.json", "LernDaten/07/joggen.json", "LernDaten/08/laufen.json",
			"LernDaten/13/laufen.json", "LernDaten/15/joggen.json", "LernDaten/16/joggen.json", "LernDaten/20/laufen.json" };

	private final String[] files_treppe_lern = { "LernDaten/02/treppe.json", "LernDaten/07/treppe.json", "LernDaten/08/treppe.json",
			"LernDaten/13/treppe.json", "LernDaten/15/treppe.json", "LernDaten/16/treppe.json", "LernDaten/20/treppe.json" };

	private final String[] files_drehen_lern = { "LernDaten/02/drehen.json", "LernDaten/07/drehen-cw.json", "LernDaten/07/drehen-ccw.json",
			"LernDaten/08/drehen.json", "LernDaten/13/drehen.json", "LernDaten/15/drehen-cw.json", "LernDaten/13/drehen.json", "LernDaten/15/drehen-ccw.json",
			"LernDaten/16/drehen.json", "LernDaten/20/drehen.json" };

	/** The files_gehen. */
	private final String[] files_gehen_test = { "TestDaten/03/gehen3.json", "TestDaten/04/gehen4.json", "TestDaten/05/gehen5.json", "TestDaten/06/gehen6.json",
			"TestDaten/09/gehen9.json" };
	
	/** The files_gehen. */
	private final String[] files_sitzen_test = { "TestDaten/03/sitzen3.json", "TestDaten/04/sitzen4.json", "TestDaten/05/sitzen5.json",
			"TestDaten/06/sitzen6.json", "TestDaten/09/sitzen9.json" };

	private final String[] files_joggen_test = { "TestDaten/03/joggen3.json", "TestDaten/04/joggen4.json", "TestDaten/05/joggen5.json",
			"TestDaten/06/joggen6.json", "TestDaten/09/joggen9.json" };

	private final String[] files_treppe_test = { "TestDaten/03/treppe3.json", "TestDaten/04/treppe4.json", "TestDaten/05/treppe5.json",
			"TestDaten/06/treppe6.json", "TestDaten/09/treppe9.json" };

	private final String[] files_drehen_test = { "TestDaten/03/drehen3.json", "TestDaten/04/drehen4.json", "TestDaten/05/drehen5.json",
			"TestDaten/06/drehen6.json", "TestDaten/09/drehen9.json" };

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
		case "drehen":
			file = files_drehen_lern;
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
		case "drehen":
			file = files_drehen_test;
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
			sensorArray[0] = (JSONArray) ((JSONObject) jsonObject.get(sensoren[0][0])).get(sensoren[0][1]);
			sensorArray[1] = (JSONArray) ((JSONObject) jsonObject.get(sensoren[1][0])).get(sensoren[1][1]);
			sensorArray[2] = (JSONArray) ((JSONObject) jsonObject.get(sensoren[2][0])).get(sensoren[2][1]);
			JSONArray[][] arrays = new JSONArray[3][sensorArray[0].size()];
			int e = 0;
			for (int i = 0; i < sensorArray[0].size(); i++) {
				for (e = 0; e < sensoren.length; e++) {
					// System.out.println(file + " " + this.valueTags[i] + ": "
					// + (JSONArray) ((JSONObject)
					// sensorArray[e].get(i)).get(this.valueTags[i]));
					arrays[e][i] = (JSONArray) ((JSONObject) sensorArray[e].get(i)).get(this.valueTags[i]);
				}
			}
			if (arrays[0] != null) {
				for (e = 0; e < arrays[0][0].size(); e++) {
					double[] d;
					Merkmal m = new Merkmal("", bewegung);
					for (int j = 0; j < arrays[0].length; j++) {
						d = new double[sensoren.length];
						for (int k = 0; k < sensoren.length; k++) {
							//System.out.println( bewegung +"Set: "+this.valueTags[j]+": " + arrays[k][j].get(e));
							d[k] = (Double) arrays[k][j].get(e);
						}
						m.setByTag(this.valueTags[j], d);
					}
					//System.out.println("Merkmal hinzugefügt für "+file +" " + bewegung);
					merkmale.add(m);
				}
			}
		} catch (Exception e) {
			System.out.println("Fehler bei file :" + file);
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
		for (Merkmal m : new InputHelper().getLernDaten("gehen")) {
			System.out.println(m.getBewegungsart() + " , " + m.getAverageAccX()[0]);
		}
		for (Merkmal m : new InputHelper().getVerificationDaten("sitzen")) {
			System.out.println(m.getBewegungsart() + " , " + m.getAverageAccX()[0]);
		}
	}

}
