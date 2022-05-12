package fr.insa.heitz.projetTreillis;

import fr.insa.heitz.projetTreillis.utils.Lire;
import java.util.ArrayList;

public abstract class Noeud {
    
    private double px;
    private double py;
    private Vecteur2D v;
    private ArrayList<Barre> barresDepart;
    private ArrayList<Barre> barresArrivee;
    
    public Noeud(double px, double py, Vecteur2D v) {
        this.px = px;
        this.py = py;
        this.v = v;
        barresDepart = new ArrayList<Barre>();
        barresArrivee = new ArrayList<Barre>();
    }
   
    public Noeud(double px, double py) {
        this(px, py, new Vecteur2D(0, 0));
    }
    
    @Override
    public String toString() {
        return "pos : [" + px + "," + py + "]\nforce : " + v;
    }
    
    public double getPx() {
        return px;
    }
    
    public void setPx(double px) {
    	this.px = px;
    }
    
    public double getPy() {
        return py;
    }
    
    public void setPy(double py) {
    	this.py = py;
    }
    
    public ArrayList<Barre> getBarresDepart() {
        return barresDepart;
    }
    
    public void setBarresDepart(ArrayList<Barre> barresDepart) {
    	this.barresDepart = barresDepart;
    }
    
    public ArrayList<Barre> getBarresArrivee() {
        return barresArrivee;
    }
    
    public void setBarresArrivee(ArrayList<Barre> barresArrivee) {
    	this.barresArrivee = barresArrivee;
    }
    
    static Noeud entreeNoeud(int n) {
        System.out.print("px : ");
        double px = Lire.d();
        System.out.print("py : ");
        double py = Lire.d();
        System.out.print("vx : ");
        double vx = Lire.d();
        System.out.print("vy : ");
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
        ArrayList<Barre> listeBarres = new ArrayList<Barre>(barresDepart);
        listeBarres.addAll(barresArrivee);
        return listeBarres;
    }
}
