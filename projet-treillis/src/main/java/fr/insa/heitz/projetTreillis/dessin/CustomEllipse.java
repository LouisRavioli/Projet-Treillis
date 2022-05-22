package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class CustomEllipse extends Forme {
	
	private Point point;
	
	public CustomEllipse(Color couleur, double px, double py, double rx, double ry, Point point, boolean terrain) {
		super(point);
		this.point = point;
		if (terrain) {
			setShape(new Rectangle(px - 4, py - 4, 8, 8));
			getShape().getStyleClass().add("forme-angle");
		}
		else {
			setShape(new Ellipse(px, py, rx, ry)); 
			getShape().getStyleClass().add("forme-point");
		}
		getShape().setFill(couleur);
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
