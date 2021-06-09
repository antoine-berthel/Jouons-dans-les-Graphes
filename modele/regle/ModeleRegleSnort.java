package modele.regle;

public class ModeleRegleSnort extends ModeleRegle {
	private static final long serialVersionUID = 200L;
	
	public ModeleRegleSnort() {
		super("Snort");
		this.JouerAcoteSoit.set(false);
		this.JouerSurEnnemi.set(false);
		this.EstBlanc.set(true);
	}
}
