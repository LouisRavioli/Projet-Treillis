package fr.insa.heitz.projetTreillis;

public abstract class NoeudAppui extends Noeud {
    
    public NoeudAppui(int id, double px, double py, Vecteur2D v) {
        super(id, px, py, v);
    }
    
    public NoeudAppui(double px, double py, Vecteur2D v) {
        super(px, py, v);
    }
    
    public NoeudAppui(double px, double py) {
        super(px, py);
    }
}
