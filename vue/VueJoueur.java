package vue;

import java.util.ArrayList;

import controleur.Controleur;
import controleur.ControleurJoueur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Bouton;
import modele.Couleur;
import modele.Modele;

public class VueJoueur extends Vue {

	private ArrayList<CreerJoueur> joueurs;

	public VueJoueur(Modele m) {
		super(m);
		this.setTitle("Création des Joueurs");
		joueurs = new ArrayList<>();
		BorderPane main = creerBorderPane(false);
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setFitToWidth(true);
		scroll.setPrefHeight(480);
		TilePane tilePane = creerTilePane(Pos.TOP_CENTER, false);
		Button valider = creerBouton("Valider");
		valider.setAlignment(Pos.CENTER);
		boutons.put(valider, Bouton.VALIDER);
		valider.setStyle("-fx-font-size: 30px;");
		for (int i = 0; i < modele.getRegleCourant().NbJoueurMax; i++) {
			joueurs.add(new CreerJoueur(i+1));
		}
		tilePane.getChildren().addAll(joueurs);
		scroll.setContent(tilePane);
		main.setTop(scroll);
		main.setCenter(valider);
		root.setCenter(main);
	}
	
	public class CreerJoueur extends VBox {
		private TextField nomJoueur;
		private Slider rouge;
		private Slider vert;
		private Slider bleu;
		private Rectangle r;
		
		public CreerJoueur(int i) {
			this.setAlignment(Pos.CENTER);
			this.setSpacing(40);
			HBox boxCouleur = new HBox();
			boxCouleur.setSpacing(20);
			boxCouleur.setAlignment(Pos.CENTER);
			rouge = creerSlider();
			vert = creerSlider();
			bleu = creerSlider();
			boxCouleur.getChildren().addAll(rouge, vert, bleu);
			r = new Rectangle(80, 40);
			r.setStrokeWidth(3);
			r.setStroke(Color.BLACK);
			r.setFill(Color.WHITE);
			nomJoueur = creerZoneText("Joueur " + i);
			nomJoueur.setStyle("-fx-font-size: 20px;");
			nomJoueur.setAlignment(Pos.CENTER);
			nomJoueur.setMaxWidth(350);
			VBox v = creerVBox(Pos.CENTER, 40);
			v.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
					+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: LightGray;");
			v.getChildren().addAll(nomJoueur, r, boxCouleur);
			this.getChildren().addAll(v);
		}
		
		public TextField getNomJoueur() {
			return nomJoueur;
		}
		
		public Couleur getCouleur() {
			return new Couleur(rouge.getValue()/255, vert.getValue()/255, bleu.getValue()/255);
		}
	}

	@Override
	public void update() {
		for (CreerJoueur joueur : joueurs) {
			joueur.r.setFill(joueur.getCouleur().toColor());
		}
	}
	
	@Override
	public Controleur creationControleur() {
		return new ControleurJoueur(this);
	}

	public ArrayList<CreerJoueur> getJoueurs() {
		return joueurs;
	}
}
