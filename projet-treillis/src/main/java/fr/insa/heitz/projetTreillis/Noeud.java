package fr.insa.heitz.projetTreillis;

import fr.insa.heitz.projetTreillis.utils.Lire;
import java.util.ArrayList;

public abstract class Noeud {
    
    private int id;
    private double px;
    private double py;
    private Vecteur2D v;
    private ArrayList<Barre> barresDepart;
    private ArrayList<Barre> barresArrivee;
    
    public Noeud(int id, double px, double py, Vecteur2D v) {
        this.id = id;
        this.px = px;
        this.py = py;
        this.v = v;
        barresDepart = new ArrayList<Barre>();
        barresArrivee = new ArrayList<Barre>();
    }
    
    public Noeud(double px, double py, Vecteur2D v) {
        this(-1, px ,py, v);
    }
    
    public Noeud(double px, double py) {
        this(px, py, new Vecteur2D(0, 0));
    }
    
    @Override
    public String toString() {
        return "id:" + id + ";pos:[" + px + "," + py + "];force:" + v;
    }
    
    public int getId() {
        return id;
    }
    
    public double getPx() {
        return px;
    }
    
    public double getPy() {
        return py;
    }
    
    public ArrayList<Barre> getBarresDepart() {
        return barresDepart;
    }
    
    public ArrayList<Barre> getBarresArrivee() {
        return barresArrivee;
    }
    
    static Noeud entreeNoeud(int n) {
        System.out.println("px:");
        double px = Lire.d();
        System.out.println("py:");
        double py = Lire.d();
        System.out.println("vx:");
        double vx = Lire.d();
        System.out.println("vy:");
        double vy = Lire.d();
        switch (n) {
            case 0 : {
                return new NoeudSimple(px, py, new Vecteur2D(vx, vy));
            }
            case 1 : {
                return new NoeudAppuiSimple(px, py, new Vecteur2D(vx, vy));
            }
            default : {
                return new NoeudAppuiGlissant(px, py, new Vecteur2D(vx, vy));
            }
        }
    }
    
    public abstract int nbrInconnues();
    
    public ArrayList<Barre> barresIncidentes() {
        ArrayList<Barre> listeBarres = barresDepart;
        listeBarres.addAll(barresArrivee);
        return listeBarres;
    }
}
