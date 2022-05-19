package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.shape.Shape;

public abstract class Forme {

	private Shape shape;
	private FigureSimple figureSimple;
	
	public Forme(Shape shape, FigureSimple figureSimple) {
		this.shape = shape;
		this.figureSimple = figureSimple;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public FigureSimple getFigureSimple() {
		return figureSimple;
	}

	public void setFigureSimple(FigureSimple figureSimple) {
		this.figureSimple = figureSimple;
	}
}
