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

	@Override
	public void remplirMatrice(Matrice m, int ligne, int colonne) {
		m.setCoeff(ligne, colonne, Math.cos(Math.PI/2 + getTerrain().angle(getTerrain().getNoeudDepart())));
		m.setCoeff(ligne + 1, colonne, Math.sin(Math.PI/2 + getTerrain().angle(getTerrain().getNoeudDepart())));
	}
	
	@Override
	public Noeud fromString(String s) {
		return null;
	}
}
