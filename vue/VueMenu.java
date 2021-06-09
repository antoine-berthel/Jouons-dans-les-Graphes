package vue;

import java.util.LinkedList;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import modele.Modele;
import modele.point.Point;
import modele.segment.Segment;

public abstract class VueMenu extends VueRetour {

	private Pane graphe;
	protected VBox top;
	protected VBox bottom;

	public VueMenu(Modele m) {
		super(m);
		this.setTitle("Menu");
		top = creerVBox(Pos.CENTER, 20);
		bottom = creerVBox(Pos.CENTER, 20);
		SplitPane main = new SplitPane();
		main.setOrientation(Orientation.HORIZONTAL);
		main.setDividerPositions(0.);
		BorderPane menu = creerBorderPane(true);
		bottom.setStyle("-fx-padding: 5;");
		bottom.getChildren().addAll(sauvegarder, retour);
		menu.setTop(top);
		menu.setBottom(bottom);
		root.setLeft(menu);
		graphe = creerPane(true);
		graphe.addEventHandler(DragEvent.DRAG_DROPPED, controleur);
		graphe.addEventHandler(DragEvent.DRAG_OVER, controleur);
		graphe.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
		menu.maxWidthProperty().set(150.);
		menu.minWidthProperty().set(150.);
		main.getItems().addAll(menu, graphe);
		root.setCenter(main);
	}

	public void update() {
		effacerTout();
		majListe();

	}

	public void effacerTout() {
		graphe.getChildren().clear();
		cercles.clear();
		lignes.clear();
	}

	@Override
	public Button creerBouton(String nom) {
		Button b = super.creerBouton(nom);
		b.setMaxWidth(Double.MAX_VALUE);
		return b;
	}

	public void majListe() {
		if (modele == null)
			return;
		for (Segment s : modele.getSegments()) {
			Line l = s.toLine();
			l.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
			lignes.put(l, s);
			this.graphe.getChildren().add(l);
		}
		for (Point p : modele.getPoints()) {
			Circle c = p.toCircle();
			c.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
			c.addEventHandler(MouseEvent.DRAG_DETECTED, controleur);
			cercles.put(c, p);
			this.graphe.getChildren().add(c);
		}
	}

	public void putPointOnTop(LinkedList<Circle> l) {
		for (int i = 0; i < cercles.size(); i++) {
			l.get(i).toFront();
		}
	}

	public Pane getGraphe() {
		return graphe;
	}
}
