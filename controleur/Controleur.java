package controleur;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import modele.Bouton;
import modele.Modele;
import vue.Vue;

public abstract class Controleur implements EventHandler<InputEvent> {

	protected Modele modele;
	protected Vue vue;
	protected Bouton bouton;

	public Controleur(Vue vue) {
		this.modele = vue.getModele();
		this.vue = vue;
		this.bouton = null;
	}

	public void handle(InputEvent event) {
		event.consume();
		Object source = event.getSource();
		if (source instanceof Button && vue.getBoutons().containsKey(source)) {
			if (bouton == vue.getBoutons((Button) source)) {
				bouton = null;
			} else {
				bouton = vue.getBoutons((Button) source);
			}
		}
	}

	public void exit() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				bouton = null;
				vue.close();
			}
		});
	}
}
