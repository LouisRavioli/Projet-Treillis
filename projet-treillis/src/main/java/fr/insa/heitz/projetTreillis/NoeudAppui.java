package fr.insa.heitz.projetTreillis;

public abstract class NoeudAppui extends Noeud {
    
    public NoeudAppui(double px, double py, Vecteur2D v) {
        super(px, py, v);
    }
    
    public NoeudAppui(double px, double py) {
        super(px, py);
    }
}
