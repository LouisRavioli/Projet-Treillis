package fr.insa.heitz.projetTreillis;

public class NoeudAppuiGlissant extends NoeudAppui {
	
    public NoeudAppuiGlissant(double px, double py, Vecteur2D v) {
        super(px, py, v);
    }
    
    public NoeudAppuiGlissant(double px, double py) {
        super(px, py);
    }
    
    @Override
    public int nbrInconnues() {
        return 1;
    }
}
