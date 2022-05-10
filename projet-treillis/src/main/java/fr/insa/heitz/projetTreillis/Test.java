package fr.insa.heitz.projetTreillis;

public class Test {
    
    public static void main(String[] args) {
    	
    	/*Matrice m = new Matrice(3, 4);
		
		m.setCoeff(0, 0, 0);
		m.setCoeff(0, 1, 1);
		m.setCoeff(0, 2, 1);
		m.setCoeff(0, 3, 1);
		m.setCoeff(1, 0, 3);
		m.setCoeff(1, 1, 4);
		m.setCoeff(1, 2, 5);
		m.setCoeff(1, 3, 3);
		m.setCoeff(2, 0, 6);
		m.setCoeff(2, 1, 5);
		m.setCoeff(2, 2, 8);
		m.setCoeff(2, 3, 4);
		
		System.out.println(m + "\n");
		try {
			m.descenteGauss();
		}
		catch (Exception e ){
			System.out.println(e);
		}
		System.out.println(m + "\n");
		m.unitaire();
		System.out.println(m + "\n");
		try {
			m.remonteeGauss();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(m);*/
		
        /*NoeudSimple n = new NoeudSimple(1, 2, 3, new Vecteur2D(5, 9));
        System.out.println(n.nbrInconnues());*/
        
        NoeudSimple n = new NoeudSimple(0, 0, new Vecteur2D(5, 9));
        
        Barre b = new Barre(n, new NoeudSimple(2, 2, new Vecteur2D(4, 8)), 5, 6, 3);
        System.out.println(b.angle(n));
    }
}
