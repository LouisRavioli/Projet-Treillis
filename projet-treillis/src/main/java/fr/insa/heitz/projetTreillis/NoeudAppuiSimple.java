package fr.insa.heitz.projetTreillis;

public class NoeudAppuiSimple extends NoeudAppui {
    
    public NoeudAppuiSimple(double px, double py, Vecteur2D v) {
        super(px, py, v);
    }
    
    public NoeudAppuiSimple(double px, double py) {
        super(px, py);
    }
    
    @Override
    public int nbrInconnues() {
        return 2;
    }
}
