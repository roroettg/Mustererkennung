package adaptivesysteme.KMeans.Test;

import adaptivesysteme.KMeans.CodeBook;
import adaptivesysteme.KMeans.KMeans;
import adaptivesysteme.KMeans.MeansVektor;
import adaptivesysteme.KMeans.NormEuklid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * The Class TestKVectoren.
 */
public class TestKVectoren extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2182509453500813181L;
	
	/** Der KMeans Algorithmus */
	private KMeans mean;

	/** Die Scalierung des Koordinatenfeldes */
	private int scale = 100;
	
	/** Die Verschiebung des Koordinatenfeld in X-Richtung. */
	private int diffx = 350;
	
	/** Die Verschiebung des Koordinatenfeld in Y-Richtung. */
	private int diffy = 350;

	/**
	 * Instantiates a new test k vectoren.
	 *
	 * @param means the means
	 */
	public TestKVectoren(KMeans means) {
		this.mean = means;
	}

	/**
	 * Hilfsfunktion, die das Koordintenkreuz und Beschriftung einzeichnet
	 *
	 * @param g the g
	 */
	private void paintAxis(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawLine(diffx, (2 * diffy) - 5, diffx, 5);
		g2d.drawLine(5, diffy, (diffx * 2) - 5, diffy);
		for (Integer i = 1; true; i++) {
			if (diffy - (scale * i) < 0)
				break;
			g2d.drawLine(diffx + 5, diffy - (scale * i), diffx - 5, diffy - (scale * i));
			g2d.drawChars(i.toString().toCharArray(), 0, i.toString().toCharArray().length, diffx + 10, diffy - (scale * i)+5);
			g2d.drawLine(diffx + 5, diffy + (scale * i), diffx - 5, diffy + (scale * i));
			g2d.drawChars(("-" + i.toString()).toCharArray(), 0, i.toString().toCharArray().length + 1, diffx + 10, diffy + (scale * i)+5);

			g2d.drawLine(diffx + (scale * i), diffy + 5, diffx + (scale * i), diffy - 5);
			g2d.drawChars(i.toString().toCharArray(), 0, i.toString().toCharArray().length, diffx + (scale * i), diffy + 15);
			g2d.drawLine(diffx - (scale * i), diffy + 5, diffx - (scale * i), diffy - 5);
			g2d.drawChars(("-" + i.toString()).toCharArray(), 0, i.toString().toCharArray().length + 1, diffx - (scale * i), diffy + 15);
		}
	}

	/* (nicht-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setBackground(Color.white);
		paintAxis(g2d);
		//Farbe je nach Vektortyp setzen
		for (MeansVektor v : this.mean.getVector()) {
			switch ((int) v.getTyp()) {
			case 1:
				g2d.setColor(Color.RED);
				break;
			case 2:
				g2d.setColor(Color.BLUE);
				break;
			case 3:
				g2d.setColor(Color.GREEN);
				break;
			default:
				g2d.setColor(Color.BLACK);
				break;
			}
			//einzeichnen des Vectors
			g2d.fillOval((int) (diffx + (v.getValue(0) * this.scale)), (int) (diffy - (v.getValue(1) * this.scale)), 5, 5);
		}
		//Einzeichnen der Codebookvectoren
		Integer i = 1;
		for (CodeBook c : this.mean.getCodeBooks()) {
			g2d.setColor(Color.YELLOW);
			g2d.fillOval((int) (diffx + (c.getValue(0) * this.scale)), (int) (diffy-(c.getValue(1) * this.scale)), 15, 15);
			g2d.setColor(Color.BLACK);
			g2d.drawChars(i.toString().toCharArray(), 0, 1, (int) (diffx + (c.getValue(0) * this.scale)), (int) (diffy - (c.getValue(1) * this.scale)));
			//Zeichnen des Umgebung der zugeordneten Vectoren
			double dist = c.meansDistanceTo() * this.scale;
			int leftup = (int) (diffx+((c.getValue(0) * this.scale) - (dist)));
			int leftdown = (int) (diffy-((c.getValue(1) * this.scale) + (dist)));
			g2d.drawOval(leftup, leftdown, (int) (dist * 2), (int) (dist * 2));
			i++;
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Vectors");
		int vectorAnzahl = 90;
		//Erzeugen der Vectoren vom typ1 und verschieben zu dem Punkt (1,1)
		ArrayList<MeansVektor> vector = KMeans.createVektors(vectorAnzahl / 3, 1, 1, 1);
		vector.addAll(KMeans.createVektors(vectorAnzahl / 3, 2, -1, 1));
		vector.addAll(KMeans.createVektors(vectorAnzahl / 3, 3, 0.5, -0.5));
		KMeans mean = new KMeans(3, vector, 2, new NormEuklid());
		frame.add(new TestKVectoren(mean));
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int i = 0;
		do {
			i++;
			// Codebuch dem Vector zuweisen
			System.out.println("Iteration: " + i);
			for (int j = 0; j < mean.getCodeBooks().length; j++) {
				System.out.println(mean.getCodeBooks()[j].toString());
			}
			frame.repaint();
			Thread.sleep(2000);
		} while (mean.getCalcCodeBook() != 0);
		System.out.println("Ende");
	}
}
