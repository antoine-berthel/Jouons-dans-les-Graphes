package modele.segment;

import javafx.scene.shape.Line;
import modele.Couleur;
import modele.estColoriable;
import modele.point.Point;

public class SegmentCouleur extends Segment implements estColoriable {
	private static final long serialVersionUID = 21L;
	
	private Couleur couleur;

	public SegmentCouleur(Point point1, Point point2, boolean oriente, Couleur couleur) {
		super(point1, point2, oriente);
		this.couleur = couleur;
	}

	public SegmentCouleur(Point point1, Point point2, Couleur couleur) {
		super(point1, point2);
		this.couleur = couleur;
	}

	@Override
	public Couleur getCouleur() {
		return couleur;
	}

	@Override
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public Line toLine() {
		Line l = super.toLine();
		l.setStroke(couleur.toColor());
		return l;
	}
}
