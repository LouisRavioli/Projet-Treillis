package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;

public class Banque {

	public static final Point P1 = new Point(1, Color.BLACK, 100, 200, null);
	public static final Point P2 = new Point(2, Color.BLACK, 400, 300, null);
	public static final Point P3 = new Point(3, Color.BLACK, 500, 200, null);
	public static final Point P4 = new Point(4, Color.BLACK, 200, 700, null);
	public static final Point P5 = new Point(5, Color.BLACK, 10, 0, null);
	public static final Point P6 = new Point(6, Color.BLACK, 80, 90, null);
	
	public static final Segment S1 = new Segment(7, Color.BLACK, P1, P2, null);
	public static final Segment S2 = new Segment(8, Color.BLACK, P3, P4, null);
	public static final Segment S3 = new Segment(9, Color.BLACK, P5, P6, null);
}
