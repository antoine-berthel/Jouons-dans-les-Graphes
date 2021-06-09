package modele.point;

import javafx.scene.shape.Circle;
import modele.Couleur;
import modele.estColoriable;

public class PointCouleur extends Point implements estColoriable {
	private static final long serialVersionUID = 11L;

	private Couleur couleur;

	public PointCouleur(double x, double y, double rayon, boolean deplacable, Couleur couleur) {
		super(x, y, rayon, deplacable);
		this.couleur = couleur;
	}

	public PointCouleur(double x, double y, double rayon, Couleur couleur) {
		super(x, y, rayon);
		this.couleur = couleur;
	}

	public PointCouleur(double x, double y, Couleur couleur) {
		super(x, y);
		this.couleur = couleur;
	}
	
	public PointCouleur(double x, double y) {
		super(x,y);
		this.couleur = Couleur.BLANC;
	}
	
	@Override
	public Circle toCircle() {
		Circle c = super.toCircle();
		c.setFill(couleur.toColor());
		return c;
	}

	@Override
	public Couleur getCouleur() {
		return couleur;
	}

	@Override
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
}
