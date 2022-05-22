package fr.insa.heitz.projetTreillis;

public class NoeudSimple extends Noeud {
    
    public NoeudSimple(double px, double py, Vecteur2D v) {
        super(px, py, v);
    }
    
    public NoeudSimple(double px, double py) {
        super(px, py);
    }
    
    public NoeudSimple() {
    	super();
	}
    
    @Override
    public int nbrInconnues() {
        return 0;
    }

	@Override
	public void remplirMatrice(Matrice m, int ligne, int colonne) {
	}
}
