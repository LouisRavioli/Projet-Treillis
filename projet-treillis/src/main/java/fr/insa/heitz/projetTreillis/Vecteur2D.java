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
}
