package vue;

import java.util.HashMap;
import java.util.Map;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modele.Bouton;
import modele.Modele;
import modele.point.Point;
import modele.segment.Segment;

public abstract class Vue extends Stage {

	protected Modele modele;
	protected Controleur controleur;
	protected Map<Circle, Point> cercles;
	protected Map<Line, Segment> lignes;
	protected Map<Button, Bouton> boutons;
	protected BorderPane root;

	public Vue(Modele m) {
		root = new BorderPane();
		Scene scene = new Scene(root, 800, 600);
		modele = m;
		cercles = new HashMap<>();
		lignes = new HashMap<>();
		boutons = new HashMap<>();
		this.controleur = creationControleur();
		root.setStyle("-fx-padding: 10;");
		this.setScene(scene);
		this.setTitle("Vue Générale");
		this.setMaximized(true);
		this.show();
	}

	@Override
	public void close() {
		super.close();
	}	
	
	
	public ImageView creerImageView(Pane p) {
		ImageView image = new ImageView((Image)(p.snapshot(new SnapshotParameters(), null)));
		image.setPreserveRatio(true);
		return image;
	}
	
	public ImageView creerImageView(Rectangle r) {
		ImageView image = new ImageView((Image)(r.snapshot(new SnapshotParameters(), null)));
		image.setPreserveRatio(true);
		return image;
	}
	
	public Button creerBouton(String nom) {
		Button b = new Button(nom);
		b.setAlignment(Pos.CENTER);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
		return b;
	}

	public Button creerBouton(String nomPoint, String nomImage) {
		return creerBouton(nomPoint, nomImage, 30, 30);
	}

	public Button creerBouton(String nomPoint, String nomImage, int height, int width) {

		Image image = new Image("images/" + nomImage);
		ImageView view = new ImageView(image);
		view.setFitHeight(height);
		view.setFitWidth(width);
		Button b = new Button(nomPoint, view);
		b.setMaxWidth(Double.MAX_VALUE);
		b.setAlignment(Pos.TOP_CENTER);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
		return b;
	}
	
	public Button creerBouton(String nomPoint, ImageView image, int height, int width) {
		image.setFitHeight(height);
		image.setFitWidth(width);
		Button b = new Button(nomPoint, image);
		b.setMaxWidth(Double.MAX_VALUE);
		b.setAlignment(Pos.TOP_CENTER);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
		return b;
	}

	public TextField creerZoneText(String nom, Double taille) {
		TextField zone = new TextField();
		zone.setPromptText(nom);
		zone.setPrefHeight(taille);
		zone.setFocusTraversable(false);
		return zone;
	}

	public TextField creerZoneText(String nom) {
		TextField zone = new TextField();
		zone.setPromptText(nom);
		zone.setFocusTraversable(false);
		return zone;
	}

	public CheckBox creerCheckBox(String nom) {
		CheckBox c = new CheckBox(nom);
		c.setStyle("-fx-font-size: 25px;" + "-fx-padding: 5 0 5 0;");
		c.setId("123");
		return c;
	}

	public CheckBox creerCheckBoxUnderline(String nom) {
		CheckBox c = new CheckBox(nom);
		c.setId("");
		c.setStyle("-fx-font-size: 25px;" + "-fx-font-weight: bold;" + "-fx-padding: 5 0 5 0;");
		return c;
	}

	public VBox creerVBox(Pos p) {
		VBox b = new VBox();
		b.setAlignment(p);
		return b;
	}

	public VBox creerVBox(Pos p, int spacing) {
		VBox b = creerVBox(p);
		b.setSpacing(spacing);
		return b;
	}

	public ScrollPane creerScrollPane() {
		ScrollPane scroll = new ScrollPane();
		scroll.setFitToWidth(true);
		scroll.setFitToHeight(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		return scroll;
	}

	public GridPane creerGridPane(Pos p, boolean Border) {
		GridPane grid = new GridPane();
		grid.setAlignment(p);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		if (Border) {
			grid.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");
		} else {
			grid.setStyle("-fx-padding: 10;");
		}
		return grid;
	}

	public TilePane creerTilePane(Pos p, boolean Border) {
		TilePane tile = new TilePane();
		tile.setAlignment(p);
		tile.setHgap(10);
		tile.setVgap(10);
		if (Border) {
			tile.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");
		} else {
			tile.setStyle("-fx-padding: 10;");
		}
		return tile;
	}

	public BorderPane creerBorderPane(boolean Border) {
		BorderPane pane = new BorderPane();
		if (Border)
			pane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");
		return pane;
	}

	public Pane creerPane(boolean Border) {
		Pane pane = new Pane();
		if (Border)
			pane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: black;");
		return pane;
	}
	
	public Slider creerSlider() {
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(255);
		slider.setValue(255);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(255);
		slider.setMinorTickCount(10);
		slider.setBlockIncrement(1);
		slider.addEventHandler(MouseEvent.MOUSE_RELEASED, controleur);
		return slider;
	}

	public abstract void update();

	public abstract Controleur creationControleur();

	public Modele getModele() {
		return modele;
	}

	public Map<Circle, Point> getCercles() {
		return cercles;
	}

	public Map<Line, Segment> getLignes() {
		return lignes;
	}

	public Bouton getBoutons(Button b) {
		return boutons.get(b);
	}

	public Map<Button, Bouton> getBoutons() {
		return boutons;
	}

	public Controleur getControleur() {
		return controleur;
	}
}
