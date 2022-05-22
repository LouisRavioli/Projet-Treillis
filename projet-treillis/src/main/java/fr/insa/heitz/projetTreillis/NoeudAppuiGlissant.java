package fr.insa.heitz.projetTreillis;

public class NoeudAppuiGlissant extends NoeudAppui {
		
    public NoeudAppuiGlissant(double px, double py, Vecteur2D v, Barre terrain) {
        super(px, py, v, terrain);
    }
    
    public NoeudAppuiGlissant(double px, double py, Vecteur2D v) {
        this(px, py, v, null);
    }
    
    public NoeudAppuiGlissant(double px, double py) {
        super(px, py);
    }
    
    public NoeudAppuiGlissant() {
    	super();
    }
    
    @Override
    public int nbrInconnues() {
        return 1;
    }
}
