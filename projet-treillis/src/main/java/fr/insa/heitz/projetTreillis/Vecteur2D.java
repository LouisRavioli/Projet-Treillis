package fr.insa.heitz.projetTreillis;

public class Vecteur2D {
    
    private double vx;
    private double vy;
    
    public Vecteur2D(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    @Override
    public String toString() {
        return "[" + vx + "," + vy + "]";
    }

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}
}
