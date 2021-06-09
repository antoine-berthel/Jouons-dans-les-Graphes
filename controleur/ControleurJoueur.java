package controleur;

import java.util.Map;

import javafx.scene.input.InputEvent;
import modele.Bouton;
import modele.Couleur;
import modele.Joueur;
import modele.point.Point;
import modele.point.PointCouleur;
import vue.Vue;
import vue.VueJeu;
import vue.VueJoueur;
import vue.VueJoueur.CreerJoueur;

public class ControleurJoueur extends ControleurRetour {
	
	
	public ControleurJoueur(Vue vue) {
		super(vue);
	}
	
	@Override
	public void handle(InputEvent event) {
		super.handle(event);
		if (bouton == Bouton.VALIDER) {
			VueJoueur vueJoueur = (VueJoueur) vue;
			for (CreerJoueur creerJoueur : vueJoueur.getJoueurs()) {
				String nom;
				if(creerJoueur.getNomJoueur().getText().equals("")) {
					 nom =creerJoueur.getNomJoueur().getPromptText();
				}else {
					nom = creerJoueur.getNomJoueur().getText();
				}
				
				Joueur joueur = new Joueur(nom, creerJoueur.getCouleur());
				if(modele.containsJoueur(joueur) || creerJoueur.getCouleur().equals(Couleur.BLANC)) {
					modele.clearJoueurs();
					vue.update();
					return;
				}
				modele.addJoueur(joueur);
			}
			for (Map.Entry<String, Point> entry : modele.getPointsSpeciaux().entrySet()) {
				for (int i = 0; i < modele.getNbJoueurs(); i++) {
					if(entry.getKey().equals("depart" + i) || entry.getKey().equals("arrive" + i)) {
						((PointCouleur) entry.getValue()).setCouleur(modele.getJoueur(i).getCouleur());
					}
				}
			}
			exit();
			new VueJeu(modele);
		}
		vue.update();
	}
}
