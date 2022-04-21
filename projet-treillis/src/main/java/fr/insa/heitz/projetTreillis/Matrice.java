package fr.insa.heitz.projetTreillis;

public class Matrice {

	private int nl;
	private int nc;
	private double[][] coeffs;
	
	public Matrice(int nl, int nc) {
		this.nl = nl;
		this.nc = nc;
		coeffs = new double[nl][nc];
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < nl; i++) {
			s += "[";
			for (int j = 0; j < nc - 1; j++) {
				s += String.format("%+4.2E ", coeffs[i][j]);
			}
			s += String.format("%+4.2E]\n", coeffs[i][nc - 1]);
		}
		return s.substring(0, s.length() - 1);
	}
	
	public int getNl() {
		return nl;
	}
	
	public void setNl(int nl) {
		this.nl = nl;
	}
	
	public int getNc() {
		return nc;
	}
	
	public void setNc(int nc) {
		this.nc = nc;
	}
	
	public double getCoeff(int i, int j) {
		return coeffs[i][j];
	}
	
	public void setCoeff(int i, int j, double x) {
		coeffs[i][j] = x;
	}
	
	public int lignePlusGrandPivot(int j) {
		int lMax = -1;
		double pivotMax = 0;
		for (int i = j; i < nl; i++) {
			if (Math.abs(coeffs[i][j]) > pivotMax) {
				pivotMax = Math.abs(coeffs[i][j]);
				lMax = i;
			}
		}
		return lMax;
	}
	
	public void permuteLigne(int i, int j) {
		for (int k = 0; k < nc; k++) {
			double temp = coeffs[i][k];
			coeffs[i][k] = coeffs[j][k];
			coeffs[j][k] = temp;
		}
	}
	
	public void transvection(int i, int j, double d) {
		for (int k = 0; k < nc; k++) {
			coeffs[i][k] -= d*coeffs[j][k];
		}
	}
	
	public void descenteGauss() throws Exception {
		for (int i = 0; i < nl - 1; i++) {
			if (lignePlusGrandPivot(i) != -1) {
				permuteLigne(i, lignePlusGrandPivot(i));
				for (int j = i + 1; j < nl; j++) {
					transvection(j, i, coeffs[j][i]/coeffs[i][i]);
				}
			}
			else {
				throw new Exception("Pivot nul : ligne " + i);
			}
		}
	}
	
	public void unitaire() {
		for (int i = 0; i < nl; i++) {
			if (coeffs[i][i] != 0) {
				double temp = coeffs[i][i];
				for (int j = i; j < nc; j++) {
					coeffs[i][j] /= temp;
				}
			}
		}
	}
	
	public void remonteeGauss() throws Exception {
		for (int i = nl - 1; i > 0; i--) {
			if (coeffs[i][i] != 0) {
				for (int j = i - 1; j >= 0; j--) {
					transvection(j, i, coeffs[j][i]);
				}
			}
			else {
				throw new Exception("Pivot nul : ligne " + i);
			}
		}
	}
}
