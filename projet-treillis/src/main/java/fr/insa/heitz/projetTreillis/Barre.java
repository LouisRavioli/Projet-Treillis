package fr.insa.heitz.projetTreillis;

public class Barre {
    
    private Noeud noeudDepart;
    private Noeud noeudArrivee;
    private double tracMax;
    private double compMax;
    private double cout;
    private Treillis treillis;
    
    public Barre(Noeud noeudDepart, Noeud noeudArrivee, double tracMax, double compMax, double cout) {
        this.noeudDepart = noeudDepart;
        noeudDepart.getBarresDepart().add(this);
        this.noeudArrivee = noeudArrivee;
        noeudArrivee.getBarresArrivee().add(this);
        this.tracMax = tracMax;
        this.compMax = compMax;
        this.cout = cout;
    }
    
    public Barre(Noeud noeudDepart, Noeud noeudArrivee) {
    	this(noeudDepart, noeudArrivee, 0, 0, 0);
    }
    
    public Barre() {
    	this(new NoeudSimple(), new NoeudSimple());
    }
    
    @Override
    public String toString() {
        return "noeud départ :\n" + noeudDepart + "\nnoeud arrivée :\n" + noeudArrivee + "\ntraction max : " + tracMax + "\ncompression max : " + compMax + "\ncoût : " + cout;
    }
    
	public Noeud getNoeudDepart() {
		return noeudDepart;
	}

	public void setNoeudDepart(Noeud noeudDepart) {
		this.noeudDepart = noeudDepart;
	}

	public Noeud getNoeudArrivee() {
		return noeudArrivee;
	}

	public void setNoeudArrivee(Noeud noeudArrivee) {
		this.noeudArrivee = noeudArrivee;
	}

	public double getTracMax() {
		return tracMax;
	}

	public void setTracMax(double tracMax) {
		this.tracMax = tracMax;
	}

	public double getCompMax() {
		return compMax;
	}

	public void setCompMax(double compMax) {
		this.compMax = compMax;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}
	
	public Treillis getTreillis() {
		return treillis;
	}

	public void setTreillis(Treillis treillis) {
		this.treillis = treillis;
	}

	public Noeud noeudOppose(Noeud n) {
        if (noeudDepart == n) {
            return noeudArrivee;
        }
        else {
            return noeudDepart;
        }
    }
    
    public double angle(Noeud n) {
        double x = noeudOppose(n).getPx() - n.getPx();
        double y = noeudOppose(n).getPy() - n.getPy();
        if (x > 0) {
            return Math.atan(y/x);
        }
        else if (x < 0) {
            return Math.PI + Math.atan(y/x);
        }
        else if (y > 0) {
            return Math.PI/2;
        }
        else {
            return -Math.PI/2;
        }
    }    
}
