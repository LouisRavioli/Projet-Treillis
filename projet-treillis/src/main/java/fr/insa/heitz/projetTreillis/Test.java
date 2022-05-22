package fr.insa.heitz.projetTreillis;

public class Test {
    
    public static void main(String[] args) {
    	
    	Matrice m = new Matrice(3, 4);
		
		m.setCoeff(0, 0, 1);
		m.setCoeff(0, 1, 2);
		m.setCoeff(0, 2, 5);
		m.setCoeff(0, 3, 0);
		m.setCoeff(1, 0, 3);
		m.setCoeff(1, 1, 4);
		m.setCoeff(1, 2, 2);
		m.setCoeff(1, 3, 1);
		m.setCoeff(2, 0, 6);
		m.setCoeff(2, 1, 2);
		m.setCoeff(2, 2, 3);
		m.setCoeff(2, 3, 2);
		
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
		System.out.println(m);
    }
}
