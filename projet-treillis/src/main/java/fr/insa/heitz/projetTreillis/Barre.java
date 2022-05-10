package fr.insa.heitz.projetTreillis;

public class Barre {
    
    private int id;
    private Noeud noeudDepart;
    private Noeud noeudArrivee;
    private double tracMax;
    private double compMax;
    private double cout;
    
    public Barre(int id, Noeud noeudDepart, Noeud noeudArrivee, double tracMax, double compMax, double cout) {
        this.id = id;
        this.noeudDepart = noeudDepart;
        noeudDepart.getBarresDepart().add(this);
        this.noeudArrivee = noeudArrivee;
        noeudArrivee.getBarresArrivee().add(this);
        this.tracMax = tracMax;
        this.compMax = compMax;
        this.cout = cout;
    }
    
    public Barre(Noeud noeudDepart, Noeud noeudArrivee, double tracMax, double compMax, double cout) {
        this(-1, noeudDepart, noeudArrivee, tracMax, compMax, cout);
    }
    
    @Override
    public String toString() {
        return "id:" + id + "\nnoeud départ:" + noeudDepart + "\nnoeud arrivée:" + noeudArrivee + "\ntraction max:" + tracMax + ";compression max:" + compMax + ";coût:" + cout;
    }
    
    public Noeud noeudOppose(Noeud n) {
        if (noeudDepart.getId() == n.getId()) {
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
