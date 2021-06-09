package modele.regle;

public class ModeleRegleDemelage extends ModeleRegle{
	private static final long serialVersionUID = 203L;
	
	public ModeleRegleDemelage() {
		super("Demelage");
		this.DeplacementAutorise.set(true);
		this.ClickAutorise.set(false);
		this.SeCroise.set(true);
		this.FinDeplacement.set(true);
		this.NbJoueurMax = 1;
	}
}
