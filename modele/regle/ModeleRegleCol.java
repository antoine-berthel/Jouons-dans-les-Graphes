package modele.regle;

public class ModeleRegleCol extends ModeleRegle {
	private static final long serialVersionUID = 201L;
	
	public ModeleRegleCol() {
		super("Col");
		this.JouerAcoteEnnemi.set(false);
		this.JouerSurEnnemi.set(false);
		this.EstBlanc.set(true);
	}
}