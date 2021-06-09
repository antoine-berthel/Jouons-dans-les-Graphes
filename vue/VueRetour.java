package vue;

import javafx.scene.control.Button;
import modele.Bouton;
import modele.Modele;

public abstract class VueRetour extends Vue {

	protected Button sauvegarder;
	protected Button retour;

	public VueRetour(Modele m) {
		super(m);
		this.setTitle("Retour");
		sauvegarder = creerBouton("Sauvegarder");
		retour = creerBouton("Retour");
		boutons.put(sauvegarder, Bouton.SAUVEGARDER);
		boutons.put(retour, Bouton.RETOUR);
	}
}
