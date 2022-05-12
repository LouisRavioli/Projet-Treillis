/*package fr.insa.heitz.projetTreillis;

import java.util.HashMap;

public class Treillis {
    
    private HashMap<Noeud,Integer> noeuds;
    private HashMap<Noeud,Integer> barres;
    
    public Treillis(HashMap<Noeud,Integer> noeuds, HashMap<Noeud,Integer> barres) {
        this.noeuds = noeuds;
        this.barres = barres;
    }
    
    public Treillis() {
    	this(new HashMap<Noeud,Integer>(), new HashMap<Noeud,Integer>());
    }
    
    public HashMap<Noeud,Integer> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(HashMap<Noeud,Integer> noeuds) {
        this.noeuds = noeuds;
    }

    public HashMap<Noeud,Integer> getBarres() {
        return barres;
    }

    public void setBarres(HashMap<Noeud,Integer> barres) {
        this.barres = barres;
    }

    public int maxIdNoeud() {
        int maxId = 0;
        for (int i = 0; i < listeNoeuds.size(); i++) {
            if (listeNoeuds.get(i).getId() > maxId) {
                maxId = listeNoeuds.get(i).getId();
            }
        }
        return maxId;
    }
    
    public int maxIdBarre() {
        int maxId = 0;
        for (int i = 0; i < listeBarres.size(); i++) {
            if (listeBarres.get(i).getId() > maxId) {
                maxId = listeBarres.get(i).getId();
            }
        }
        return maxId;
    }
    
    public void ajouteNoeud(Noeud n) throws Exception {
    	boolean stocke = true;
    	for (int i = 0; i < listeNoeuds.size(); i++) {
    		if (n == listeNoeuds.get(i)) {
    			stocke = false;
    		}
    	}
    	if (stocke) {
    		n.setId(maxIdNoeud() + 1);
    		listeNoeuds.add(n);
    	}
    	else {
    		throw new Exception("Noeud déjà dans la liste");
    	}
    }
    
    public void ajouteBarre(Barre b) throws Exception {
    	boolean stocke = true;
    	for (int i = 0; i < listeBarres.size(); i++) {
    		if (b == listeBarres.get(i)) {
    			stocke = false;
    		}
    	}
    	if (stocke) {
    		ajouteNoeud(b.getNoeudDepart());
    		ajouteNoeud(b.getNoeudArrivee());
    		b.setId(maxIdBarre() + 1);
    		listeBarres.add(b);
    	}
    	else {
    		throw new Exception("Barre déjà dans la liste");
    	}
    }
    
    public void supprimeNoeud(Noeud n) throws Exception {
    	boolean contenu = false;
    	for (int i = 0; i < listeNoeuds.size(); i++) {
    		if (n == listeNoeuds.get(i)) {
    			contenu = true;
    			for (int j = 0; j < n.barresIncidentes().size(); j++) {
    				supprimeBarre(n.barresIncidentes().get(j));
    			}
    			listeNoeuds.remove(i);
    		}
    	}
    	if (!contenu) {
    		throw new Exception("Noeud absent de la liste");
    	}
    }
    
    public void supprimeBarre(Barre b) throws Exception {
    	boolean contenu = false;
    	for (int i = 0; i < listeBarres.size(); i++) {
    		if (b == listeBarres.get(i)) {
    			contenu = true;
    			listeBarres.remove(i);
    		}
    	}
    	if (!contenu) {
    		throw new Exception("Barre absente de la liste");
    	}
    }
}*/
