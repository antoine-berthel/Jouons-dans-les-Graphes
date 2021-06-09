package vue;

import controleur.Controleur;
import controleur.ControleurGagnant;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modele.Modele;

public class VueGagnant extends VueRetour {

	public VueGagnant(Modele m) {
		super(m);
		this.setTitle("Fin");
		BorderPane main = creerBorderPane(false);
		VBox box = creerVBox(Pos.CENTER, 40);
		Text finJeu = new Text(m.getJoueur(m.getJoueurPrecedent()).getNom()+ " a gagné");
		finJeu.setStyle("-fx-font-size: 80px;");
		box.getChildren().addAll(finJeu, retour);
		main.setCenter(box);
		root.setCenter(main);
		modele.resetCourant();
	}

	@Override
	public void update() {}

	@Override
	public Controleur creationControleur() {
		return new ControleurGagnant(this);
	}
}
