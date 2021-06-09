package controleur;

import java.util.Map;

import javafx.scene.control.CheckBox;
import javafx.scene.input.InputEvent;
import modele.MutableBoolean;
import vue.Vue;
import vue.VueCreationRegle;

public class ControleurRegles extends ControleurRetour {

	public ControleurRegles(Vue vue) {
		super(vue);
	}

	@Override
	public void handle(InputEvent event) {
		super.handle(event);
		reglesChoisies();
	}
	
	public void reglesChoisies() {
		VueCreationRegle vueRegle = (VueCreationRegle) vue;
		Map<CheckBox, MutableBoolean> regles = vueRegle.getReglesChoisis();
		for (Map.Entry<CheckBox, MutableBoolean> entry : regles.entrySet()) {
			entry.getValue().set(entry.getKey().isSelected());
		}
	}
}
