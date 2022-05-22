package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ZoneDessin extends Pane {
	
	private MainBorderPane bpMain;
	
	private DoubleProperty scale;
	public static final double MIN_SCALE = 0.1;
	public static final double MAX_SCALE = 10;
	
	private double mouseAnchorX;
	private double mouseAnchorY;
	private double translateAnchorX;
	private double translateAnchorY;
		
	public ZoneDessin(MainBorderPane bpMain) {
		this.bpMain = bpMain;
		
		Rectangle clip = new Rectangle();
		clip.heightProperty().bind(heightProperty());
		clip.widthProperty().bind(widthProperty());
		setClip(clip);
        
		scale = new SimpleDoubleProperty(1);
		scaleXProperty().bind(scale);
		scaleYProperty().bind(scale);
		
		dessinerTout();
		
		getStyleClass().add("zone-dessin-pane");
		
		setOnMouseMoved(move -> {
			bpMain.getControleur().moveMouseZoneDessinLine(move);
		});
		
		setOnMousePressed(dragEntered -> {
			bpMain.getControleur().clicZoneDessin(dragEntered);
			bpMain.getControleur().setPosInitiale(dragEntered);
			bpMain.getControleur().clicMoletteZoneDessin(dragEntered);
		});
		
		setOnMouseDragged(drag -> {
			bpMain.getControleur().dragMouseZoneDessinDeplacer(drag);
			bpMain.getControleur().dragMoletteZoneDessin(drag);
		});		
		
		setOnScroll(event -> {
			bpMain.getControleur().zoomZoneDessin(event);
		});
	}

	public double getScale() {
		return scale.get();
	}

	public void setScale(double scale) {
		this.scale.set(scale);
	}
	
	public double getMouseAnchorX() {
		return mouseAnchorX;
	}

	public void setMouseAnchorX(double mouseAnchorX) {
		this.mouseAnchorX = mouseAnchorX;
	}

	public double getMouseAnchorY() {
		return mouseAnchorY;
	}

	public void setMouseAnchorY(double mouseAnchorY) {
		this.mouseAnchorY = mouseAnchorY;
	}

	public double getTranslateAnchorX() {
		return translateAnchorX;
	}

	public void setTranslateAnchorX(double translateAnchorX) {
		this.translateAnchorX = translateAnchorX;
	}

	public double getTranslateAnchorY() {
		return translateAnchorY;
	}

	public void setTranslateAnchorY(double translateAnchorY) {
		this.translateAnchorY = translateAnchorY;
	}

	public void setPivot(double x, double y) {
        setTranslateX(getTranslateX() - x);
        setTranslateY(getTranslateY() - y);
    }

	public void dessinerTout() {
		getChildren().clear();
		List<Node> formes = new ArrayList<Node>();
		bpMain.getModele().dessine(bpMain.getControleur(), formes);
		getChildren().addAll(formes);
	}
}
