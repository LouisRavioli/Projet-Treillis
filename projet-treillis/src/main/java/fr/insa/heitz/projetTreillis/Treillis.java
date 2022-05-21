package fr.insa.heitz.projetTreillis;

import java.util.HashMap;

public class Treillis {
    
    private HashMap<Noeud,Integer> noeuds;
    private HashMap<Barre,Integer> barres;
    
    public Treillis(HashMap<Noeud,Integer> noeuds, HashMap<Barre,Integer> barres) {
        this.noeuds = noeuds;
        this.barres = barres;
    }
    
    public Treillis() {
    	this(new HashMap<Noeud,Integer>(), new HashMap<Barre,Integer>());
    }
    
    public HashMap<Noeud,Integer> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(HashMap<Noeud,Integer> noeuds) {
        this.noeuds = noeuds;
    }

    public HashMap<Barre,Integer> getBarres() {
        return barres;
    }

    public void setBarres(HashMap<Barre,Integer> barres) {
        this.barres = barres;
    }

    public int idLibreNoeud() {
        int maxId = 0;
        while (noeuds.containsValue(maxId)) {
            maxId++;
        }
        return maxId;
    }
    
    public int idLibreBarre() {
        int maxId = 0;
        while (barres.containsValue(maxId)) {
            maxId++;
        }
        return maxId;
    }
    
    public void ajouteNoeud(Noeud n) {
    	if (!noeuds.containsKey(n)) {
    		noeuds.put(n, idLibreNoeud());
    		n.setTreillis(this);
    	}
}
    
    public void ajouteBarre(Barre b) {
    	if (!barres.containsKey(b)) {
    		ajouteNoeud(b.getNoeudDepart());
    		ajouteNoeud(b.getNoeudArrivee());
    		barres.put(b, idLibreBarre());
    		b.setTreillis(this);
    	}
    }
    
    public void supprimeNoeud(Noeud n) {
    	if (noeuds.containsKey(n)) {
    		noeuds.remove(n);
    	}
    }
    
    public void supprimeBarre(Barre b) {
    	if (barres.containsKey(b)) {
    		barres.remove(b);
    	}
    }
}
