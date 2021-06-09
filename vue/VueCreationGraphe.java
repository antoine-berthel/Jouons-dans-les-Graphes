package vue;

import controleur.Controleur;
import controleur.ControleurGraphes;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Bouton;
import modele.Modele;

public class VueCreationGraphe extends VueMenu {

	protected TextField nomGraphe;

	public VueCreationGraphe(Modele m) {
		super(m);
		this.setTitle("Création d'un graphe");
		nomGraphe = creerZoneText("Nom du Graphe", 40.);
		ajoutBouton(creerBouton("Point", "point.png"), Bouton.POINT);
		ajoutBouton(creerBouton("Segment", "segment.png", 3, 45), Bouton.SEGMENT);
		ajoutBouton(creerBouton("Supprimer", "delete.png"), Bouton.SUPPRIMER);
		ajoutBouton(creerBouton("Supprimer\n    Tout"), Bouton.SUPPRIMERTOUT);
		bottom.getChildren().add(0, nomGraphe);
		update();
	}

	public void ajoutBouton(Button button, Bouton bouton) {
		boutons.put(button, bouton);
		top.getChildren().add(button);
	}

	@Override
	public Controleur creationControleur() {
		return new ControleurGraphes(this);
	}

	public TextField getNomGraphe() {
		return nomGraphe;
	}
}
