package fr.insa.heitz.projetTreillis;

public class NoeudAppuiSimple extends NoeudAppui {
    
    public NoeudAppuiSimple(double px, double py, Vecteur2D v, Barre terrain) {
        super(px, py, v, terrain);
    }
    
    public NoeudAppuiSimple(double px, double py, Vecteur2D v) {
        this(px, py, v, null);
    }
    
    public NoeudAppuiSimple(double px, double py) {
        super(px, py);
    }
    
    public NoeudAppuiSimple() {
    	super();
    }
    
    @Override
    public int nbrInconnues() {
        return 2;
    }
}
