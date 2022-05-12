package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Titre extends BorderPane {
    
    public Titre(String nom, String style) {
        Label lTitre = new Label(nom);
        lTitre.getStyleClass().add(style + "-label");
        Button bFermer = new Button();
        bFermer.getStyleClass().add(style + "-button");
        setLeft(lTitre);
        setRight(bFermer);
        getStyleClass().add(style + "-border-pane");
    }
}
