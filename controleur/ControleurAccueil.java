package controleur;

import Jeux.JeuModifie;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modele.Bouton;
import modele.graphe.ModeleGraphe;
import modele.regle.ModeleRegle;
import vue.Vue;
import vue.VueAccueil;
import vue.VueCreationGraphe;
import vue.VueCreationRegle;
import vue.VueJeu;
import vue.VueJoueur;

public class ControleurAccueil extends Controleur {

	public ControleurAccueil(Vue vue) {
		super(vue);
		launcherPartieEnCours();
	}

	@Override
	public void handle(InputEvent event) {
		super.handle(event);
		eventBouton();
		Object source = event.getSource();
		if (source instanceof Button) {
			eventGrapheLocal((Button) source, ((MouseEvent) event).getButton() == MouseButton.PRIMARY);
			eventGraphePredef((Button) source);
			eventRegleLocal((Button) source, ((MouseEvent) event).getButton() == MouseButton.PRIMARY);
			eventReglePredef((Button) source);
		}
		launcher();
	}

	public void eventBouton() {
		if (bouton == Bouton.ALEATOIRE) {
			int i = (int) (Math.random() * 10) + 5;
			modele.setGrapheCourant(ModeleGraphe.creerGrapheAleatoire(i, (int) (Math.random() * i), 1200, 700, 100));
			new VueCreationGraphe(modele);
			exit();
		} else if (bouton == Bouton.CREERGRAPHE) {
			modele.setGrapheCourant(new ModeleGraphe());
			new VueCreationGraphe(modele);
			exit();
		} else if (bouton == Bouton.CREERREGLE) {
			modele.setRegleCourant(new ModeleRegle());
			new VueCreationRegle(modele);
			exit();
		}
	}

	public void eventGraphePredef(Button source) {
		VueAccueil vueAccueil = (VueAccueil) vue;
		if (vueAccueil.getGraphePredef().containsKey(source)) {
			modele.setGrapheCourant((ModeleGraphe) vueAccueil.getGraphePredef(source).clone());
		}
	}

	public void eventGrapheLocal(Button source, boolean click) {
		VueAccueil vueAccueil = (VueAccueil) vue;
		if (vueAccueil.getGrapheLocal().containsKey(source)) {
			if (click) {
				modele.setGrapheCourant((ModeleGraphe) vueAccueil.getGrapheLocal(source).clone());
			} else {
				modele.getGraphesLocal().remove(vueAccueil.getGrapheLocal(source));
				vue.update();
			}
		}
	}

	public void eventReglePredef(Button source) {
		VueAccueil vueAccueil = (VueAccueil) vue;
		if (vueAccueil.getReglePredef().containsKey(source)) {
			modele.setRegleCourant((ModeleRegle) vueAccueil.getReglePredef(source).clone());
		}
	}

	public void eventRegleLocal(Button source, boolean click) {
		VueAccueil vueAccueil = (VueAccueil) vue;
		if (vueAccueil.getRegleLocal().containsKey(source)) {
			if (click) {
				modele.setRegleCourant((ModeleRegle) vueAccueil.getRegleLocal(source).clone());
			} else {
				modele.getReglesLocal().remove(vueAccueil.getRegleLocal(source));
				vue.update();
			}
		}
	}

	public void launcher() {
		if (modele.getGrapheCourant() != null && modele.getRegleCourant() != null) {
			exit();
			new VueJoueur(modele);
		}
	}

	public void launcherPartieEnCours() {
		if (modele.getGrapheCourant() != null && modele.getRegleCourant() != null) {
			exit();
			if (modele.getRegleCourant().getNom().equals("JeuModifie")) {
				new VueJeu(modele) {
					@Override
					public Controleur creationControleur() {
						return new ControleurJeu(this, new JeuModifie(this));
					}
				};
			} else {
				new VueJeu(modele);
			}
		}
	}
}
