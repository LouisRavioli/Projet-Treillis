package fr.insa.heitz.projetTreillis;

import java.util.ArrayList;

public class Treillis {
    
    private ArrayList<Noeud> listeNoeuds;
    private ArrayList<Barre> listeBarres;
    
    public Treillis(ArrayList<Noeud> listeNoeuds, ArrayList<Barre> listeBarres) {
        this.listeNoeuds = listeNoeuds;
        this.listeBarres = listeBarres;
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
    
    /*public int maxIdBarre() {
        int maxId = 0;
        for (int i = 0; i < listeBarres.size(); i++) {
            if (listeBarres.get(i).getId() > maxId) {
                maxId = listeBarres.get(i).getId();
            }
        }
        return maxId;
    }*/
}
