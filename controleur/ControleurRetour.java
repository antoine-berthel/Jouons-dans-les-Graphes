package controleur;

import java.io.IOException;

import javafx.scene.input.InputEvent;
import modele.Bouton;
import vue.Vue;
import vue.VueAccueil;
import vue.VueCreationGraphe;
import vue.VueCreationRegle;
import vue.VueMenu;

public abstract class ControleurRetour extends Controleur {

	public ControleurRetour(Vue vue) {
		super(vue);
	}

	@Override
	public void handle(InputEvent event) {
		super.handle(event);
		if (bouton == Bouton.SAUVEGARDER) {
			if (vue instanceof VueCreationGraphe) {
				VueCreationGraphe vueGraphe = (VueCreationGraphe) vue;
				String nomGraphe = vueGraphe.getNomGraphe().getText();
				if (!nomGraphe.isEmpty()) {
					modele.setGrapheNom(nomGraphe);
					modele.sauvegardeGraphe();
				}
			} else if (vue instanceof VueCreationRegle) {
				VueCreationRegle vueRegles = (VueCreationRegle) vue;
				String nomRegle = vueRegles.getNomRegleField().getText();
				if (!nomRegle.isEmpty()) {
					modele.setRegleNom(nomRegle);
					modele.sauvegardeRegle();
				}
			} else if (vue instanceof VueMenu) {
				try {
					modele.exportModele();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bouton = null;
		} else if (bouton == Bouton.RETOUR) {
			exit();
			modele.resetCourant();
			new VueAccueil(modele);
		}
	}
}
