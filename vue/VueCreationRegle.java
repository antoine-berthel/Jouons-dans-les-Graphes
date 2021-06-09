package vue;

import java.util.HashMap;
import java.util.Map;

import controleur.Controleur;
import controleur.ControleurRegles;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modele.Modele;
import modele.MutableBoolean;
import modele.regle.ModeleRegle;

public class VueCreationRegle extends VueRetour {

	private TextField nomRegleField;
	private VBox VBoxPoint;
	protected Map<CheckBox, MutableBoolean> reglesChoisis;

	public VueCreationRegle(Modele m) {
		super(m);
		this.setTitle("Création d'une règle");
		BorderPane point = creerBorderPane(true);
		VBoxPoint = creerVBox(Pos.CENTER_LEFT);
		VBoxPoint.setStyle("-fx-padding: 10;");
		reglesChoisis = new HashMap<>();
		ModeleRegle regle = modele.getRegleCourant();
		ajoutCheckBox(creerCheckBoxUnderline("Point coloriable"), regle.Coloriable);
		ajoutCheckBox(creerCheckBox("A coté de soit"), regle.JouerAcoteSoit);
		ajoutCheckBox(creerCheckBox("A coté d'un ennemi"), regle.JouerAcoteEnnemi);
		ajoutCheckBox(creerCheckBox("Sur un ennemi"), regle.JouerSurEnnemi);
		ajoutCheckBox(creerCheckBoxUnderline("Point deplacable"), regle.DeplacementAutorise);
		// reglesChoisis.put(creerCheckBox("colorier point entouré par nos points"), regle);
		BorderPane main = new BorderPane();
		SplitPane pointEtSegment = new SplitPane();
		GridPane segment = creerGridPane(Pos.CENTER_LEFT, true);
		GridPane bottom = creerGridPane(Pos.CENTER, true);
		nomRegleField = creerZoneText("Nom de la règle", 40.);
		bottom.add(retour, 0, 0);
		bottom.add(nomRegleField, 1, 0);
		bottom.add(sauvegarder, 2, 0);
		pointEtSegment.setOrientation(Orientation.HORIZONTAL);
		point.setLeft(VBoxPoint);
		point.maxWidthProperty().bind(pointEtSegment.widthProperty().multiply(0.5));
		point.minWidthProperty().bind(pointEtSegment.widthProperty().multiply(0.5));
		pointEtSegment.getItems().addAll(point, segment);
		main.setCenter(pointEtSegment);
		main.setBottom(bottom);
		root.setCenter(main);
	}

	public void ajoutCheckBox(CheckBox c, MutableBoolean b) {
		HBox h = new HBox();
		Text space = new Text("");
		if (c.getId().equals("123"))
			space.setText("\t\t");
		h.getChildren().addAll(space, c);
		reglesChoisis.put(c, b);
		VBoxPoint.getChildren().add(h);
	}

	@Override
	public Button creerBouton(String nom) {
		Button b = super.creerBouton(nom);
		b.setPrefWidth(150.);
		b.setPrefHeight(40.);
		return b;
	}

	@Override
	public void update() {

	}

	@Override
	public Controleur creationControleur() {
		return new ControleurRegles(this);
	}

	public TextField getNomRegleField() {
		return nomRegleField;
	}

	public Map<CheckBox, MutableBoolean> getReglesChoisis() {
		return reglesChoisis;
	}
}
