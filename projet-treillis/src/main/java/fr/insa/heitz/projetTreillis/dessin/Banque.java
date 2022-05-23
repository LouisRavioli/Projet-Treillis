package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;

public class Banque {

	public static final Point P1 = new Point(Color.BLACK, 100, 200, null);
	public static final Point P2 = new Point(Color.BLACK, 400, 300, null);
	public static final Point P3 = new Point(Color.BLACK, 500, 200, null);
	public static final Point P4 = new Point(Color.BLACK, 200, 700, null);
	public static final Point P5 = new Point(Color.BLACK, 10, 0, null);
	public static final Point P6 = new Point(Color.BLACK, 80, 90, null);
	
	public static final Segment S1 = new Segment(Color.BLACK, P1, P2, null);
	public static final Segment S2 = new Segment(Color.BLACK, P3, P4, null);
	public static final Segment S3 = new Segment(Color.BLACK, P5, P6, null);
	

	public static final Point n1 = new Point(40, 40);
    public static final Point n2 = new Point(120, 40);
    public static final Point n3 = new Point(200, 40);
    public static final Point n4 = new Point(280, 40);
    public static final Point n5 = new Point(80, 100);
    public static final Point n6 = new Point(160, 100);
    public static final Point n7 = new Point(240, 100);
    public static final Point n8 = new Point(120, 160);
    public static final Point n9 = new Point(200, 160);
     
    public static final Segment b1 = new Segment(n1, n3);
    public static final Segment b2 = new Segment(n1, n2);
    public static final Segment b3 = new Segment(n2, n5);
    public static final Segment b4 = new Segment(n2, n6);
    public static final Segment b5 = new Segment(n2, n3);
    public static final Segment b6 = new Segment(n3, n6);
    public static final Segment b7 = new Segment(n3, n7);
    public static final Segment b8 = new Segment(n3, n4);
    public static final Segment b9 = new Segment(n4, n7);
    public static final Segment b10 = new Segment(n5, n6);
    public static final Segment b11 = new Segment(n5, n8);
    public static final Segment b12 = new Segment(n6, n8);
    public static final Segment b13 = new Segment(n6, n9);
    public static final Segment b14 = new Segment(n6, n7);
    public static final Segment b15 = new Segment(n7, n9);
    public static final Segment b16 = new Segment(n8, n9);
    public static final Segment b17 = new Segment(n1,n5);
    
    public static final Groupe G2 = new Groupe(Banque.b1, Banque.b2, Banque.b3, Banque.b4, Banque.b5,Banque.b6,Banque.b7,Banque.b8,Banque.b9,Banque.b10,Banque.b11,Banque.b12,Banque.b13,Banque.b14,Banque.b15,Banque.b16,Banque.b17,Banque.n1,Banque.n2,Banque.n3,Banque.n4,Banque.n5,Banque.n6,Banque.n7,Banque.n8,Banque.n9);
    

    public static final Point n1a = new Point(40, 20);
    public static final Point n2a = new Point(120, 20);
    public static final Point n3a = new Point(200, 20);
    public static final Point n4a = new Point(280, 20);
    public static final Point n5a = new Point(80, 200);
    public static final Point n6a = new Point(160, 200);
    public static final Point n7a = new Point(240, 200);
    public static final Point n8a = new Point(120, 400);
    public static final Point n9a = new Point(200, 400);
     
    public static final Segment b1a = new Segment(n1a, n3a);
    public static final Segment b2a = new Segment(n1a, n2a);
    public static final Segment b3a = new Segment(n2a, n5a);
    public static final Segment b4a = new Segment(n2a, n6a);
    public static final Segment b5a = new Segment(n2a, n3a);
    public static final Segment b6a = new Segment(n3a, n6a);
    public static final Segment b7a = new Segment(n3a, n7a);
    public static final Segment b8a = new Segment(n3a, n4a);
    public static final Segment b9a = new Segment(n4a, n7a);
    public static final Segment b10a = new Segment(n5a, n6a);
    public static final Segment b11a = new Segment(n5a, n8a);
    public static final Segment b12a = new Segment(n6a, n8a);
    public static final Segment b13a = new Segment(n6a, n9a);
    public static final Segment b14a = new Segment(n6a, n7a);
    public static final Segment b15a = new Segment(n7a, n9a);
    public static final Segment b16a = new Segment(n8a, n9a);
    public static final Segment b17a = new Segment(n1a,n5a);
    
    public static final Groupe G1 = new Groupe(b1a, b2a, b3a, b4a, b5a, b6a, b7a, b8a, b9a, b10a, b11a, b12a, b13a, b14a, b15a, b16a, b17a, n1a, n2a, n3a, n4a, n5a, n6a, n7a, n8a,n9a);
}
