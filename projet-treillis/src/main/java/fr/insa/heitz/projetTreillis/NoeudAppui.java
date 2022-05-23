package fr.insa.heitz.projetTreillis;

public abstract class NoeudAppui extends Noeud {
    
	private Barre terrain;
	
    public NoeudAppui(double px, double py, Vecteur2D v, Barre terrain) {
        super(px, py, v);
        this.terrain = terrain;
    }
    
    public NoeudAppui(double px, double py) {
        super(px, py);
    }
    
    public NoeudAppui() {
    	super();
    }

	public Barre getTerrain() {
		return terrain;
	}

	public void setTerrain(Barre terrain) {
		this.terrain = terrain;
	}
	
	@Override
	public String barreToSave() {
		return String.valueOf(getTreillis().getBarres().get(terrain));
	}
}
