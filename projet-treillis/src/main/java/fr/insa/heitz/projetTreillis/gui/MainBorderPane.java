package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {

	private Haut vbMainHaut;
	private Bas hbMainBas;
	private Gauche vbMainGauche;
	private Droite vbMainDroite;
	private Centre spMainCentre;
	
	public MainBorderPane() {
		vbMainHaut = new Haut();
		hbMainBas = new Bas();
		vbMainGauche = new Gauche();
		vbMainDroite = new Droite();
		spMainCentre = new Centre();
				
		setTop(vbMainHaut);
		setBottom(hbMainBas);
		setLeft(vbMainGauche);
		setRight(vbMainDroite);
		setCenter(spMainCentre);
	}

    /**
     * @return the vbMainHaut
     */
    public Haut getVbMainHaut() {
        return vbMainHaut;
    }

    /**
     * @param vbMainHaut the vbMainHaut to set
     */
    public void setVbMainHaut(Haut vbMainHaut) {
        this.vbMainHaut = vbMainHaut;
    }

    /**
     * @return the hbMainBas
     */
    public Bas getHbMainBas() {
        return hbMainBas;
    }

    /**
     * @param hbMainBas the hbMainBas to set
     */
    public void setHbMainBas(Bas hbMainBas) {
        this.hbMainBas = hbMainBas;
    }

    /**
     * @return the vbMainGauche
     */
    public Gauche getVbMainGauche() {
        return vbMainGauche;
    }

    /**
     * @param vbMainGauche the vbMainGauche to set
     */
    public void setVbMainGauche(Gauche vbMainGauche) {
        this.vbMainGauche = vbMainGauche;
    }

    /**
     * @return the vbMainDroite
     */
    public Droite getVbMainDroite() {
        return vbMainDroite;
    }

    /**
     * @param vbMainDroite the vbMainDroite to set
     */
    public void setVbMainDroite(Droite vbMainDroite) {
        this.vbMainDroite = vbMainDroite;
    }

    /**
     * @return the spMainCentre
     */
    public Centre getSpMainCentre() {
        return spMainCentre;
    }

    /**
     * @param spMainCentre the spMainCentre to set
     */
    public void setSpMainCentre(Centre spMainCentre) {
        this.spMainCentre = spMainCentre;
    }

        
}
