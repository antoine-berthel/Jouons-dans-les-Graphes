package controleur;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import modele.Bouton;
import modele.Couleur;
import modele.point.Point;
import modele.point.PointCouleur;
import modele.segment.Segment;
import modele.segment.SegmentCouleur;
import vue.Vue;
import vue.VueMenu;

public class ControleurGraphes extends ControleurRetour {

	private Point premierPoint;

	public ControleurGraphes(Vue vue) {
		super(vue);
		this.premierPoint = null;
	}

	@Override
	public void handle(InputEvent event) {
		super.handle(event);
		event.consume();
		Object source = event.getSource();
		if (event instanceof DragEvent) {
			eventDrag((DragEvent) event, (Pane) source);
		} else if (event instanceof MouseEvent) {
			eventMouse((MouseEvent) event, source);
		}
		if (bouton == Bouton.SUPPRIMERTOUT) {
			modele.supprimerTout();
			bouton = null;
			premierPoint = null;
			vue.update();
		}
	}

	public void addPoint(double x, double y) {
		for (Point point : modele.getPoints()) {
			if (tailleSegmentMinimum(x - point.getX(), y - point.getY(), 60)) {
				return;
			}
		}
		this.modele.addPoint(new PointCouleur(x, y, Couleur.BLANC));
	}

	public boolean deplacerPoint(Point p, double x, double y) {
		for (Point point : modele.getPoints()) {
			if (tailleSegmentMinimum(x - point.getX(), y - point.getY(), 60)) {
				return false;
			}
		}
		p.setX(x);
		p.setY(y);
		return true;
	}

	public void addSegment(Point p1, Point p2) {
		this.modele.addSegment(new SegmentCouleur(p1, p2, Couleur.NOIR));
	}

	public void addPointSegment(double x, double y) {
		addPoint(x, y);
		addSegment(premierPoint, modele.getPoint(modele.getSizePoints() - 1));
	}

	public void eventCercle(MouseEvent event, Circle source) {
		Point point = vue.getCercles().get(source);
		if (bouton == Bouton.SUPPRIMER) {
			if (premierPoint.equals(point)) {
				premierPoint = null;
			}
			modele.removePoint(point);
		} else if (bouton == Bouton.SEGMENT) {
			if (premierPoint == null) {
				premierPoint = point;
			} else {
				addSegment(premierPoint, point);
				if (event.isControlDown()) {
					premierPoint = point;
				} else {
					premierPoint = null;
				}
			}
		} else if (point instanceof PointCouleur) {
			PointCouleur p = (PointCouleur) point;
			Couleur c = p.getCouleur();
			if (c.equals(Couleur.BLANC)) {
				p.setCouleur(Couleur.BLEU);
				if (modele.getPointsSpeciaux().isEmpty())
					modele.addPointSpeciaux("depart0", point);
				else
					modele.addPointSpeciaux("depart1", point);
			} else if (c.equals(Couleur.BLEU)) {
				p.setCouleur(Couleur.ROUGE);
				if (modele.getPointsSpeciaux().isEmpty()) {
					modele.removePointSpeciaux("depart0");
					modele.addPointSpeciaux("arrive0", point);
				} else {
					modele.removePointSpeciaux("depart1");
					modele.addPointSpeciaux("arrive1", point);
				}
			} else if (c.equals(Couleur.ROUGE)) {
				p.setCouleur(Couleur.BLANC);
				modele.removePointSpeciaux(point);
			}
		}
		premierPoint = point;
	}

	public void eventLine(MouseEvent event, Line source) {
		Segment segment = vue.getLignes().get(source);
		if (bouton == Bouton.SUPPRIMER) {
			modele.removeSegment(segment);
		} else if (segment instanceof SegmentCouleur) {
			((SegmentCouleur) segment).setCouleur(modele.getJoueur(modele.getJoueurCourant()).getCouleur());
		}
	}

	public void eventPane(MouseEvent event) {
		double x = event.getX();
		double y = event.getY();
		if (bouton == Bouton.POINT) {
			if (premierPoint != null) {
				double[] coor = AltShiftAction(x, y, event);
				x = coor[0];
				y = coor[1];
			}
			addPoint(x, y);
			premierPoint = modele.getPoint(modele.getSizePoints() - 1);
		} else if (bouton == Bouton.SEGMENT && premierPoint != null) {
			double[] coor = AltShiftAction(x, y, event);
			x = coor[0];
			y = coor[1];
			if (!event.isControlDown()) {
				addPointSegment(x, y);
			}
			premierPoint = modele.getPoint(modele.getSizePoints() - 1);
		} else {
			premierPoint = null;
		}
	}

	public void eventDrag(DragEvent event, Pane source) {
		if (source instanceof Pane) {
			if (event.getEventType() == DragEvent.DRAG_OVER) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			} else if (event.getEventType() == DragEvent.DRAG_DROPPED) {
				Circle cercle = (Circle) event.getGestureSource();
				Point p = vue.getCercles().get(cercle);
				event.setDropCompleted(deplacerPoint(p, event.getX(), event.getY()));
				vue.update();
			}
		}
	}

	public void eventMouse(MouseEvent event, Object source) {
		if (event.getEventType() == MouseEvent.DRAG_DETECTED) {
			if (source instanceof Circle && vue.getCercles().containsKey(source)) {
				Dragboard db = ((Circle) source).startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("");
				db.setContent(content);
			}
		} else {
			if (source instanceof Circle && vue.getCercles().containsKey(source)) {
				eventCercle(event, (Circle) source);
				vue.update();
			} else if (source instanceof Line && vue.getLignes().containsKey(source)) {
				eventLine(event, (Line) source);
				vue.update();
			} else if (vue instanceof VueMenu && source instanceof Pane && source == ((VueMenu) vue).getGraphe()) {
				eventPane(event);
				vue.update();
			}
		}
	}
	
	public double[] AltShiftAction(double x, double y, MouseEvent event) {
		if (event.isShiftDown()) {
			if (tailleAxeMinimum(x, premierPoint.getX(), 30)) {
				x = premierPoint.getX();
			} else if (tailleAxeMinimum(y, premierPoint.getY(), 30)) {
				y = premierPoint.getY();
			} else {
				x = premierPoint.getX() + diagonale(x - premierPoint.getX(), y - premierPoint.getY());
				y = premierPoint.getY() + diagonale(y - premierPoint.getY(), x - premierPoint.getX());
			}
		}
		if (event.isAltDown()) {
			x = premierPoint.getX() + tailleFixe(x - premierPoint.getX(), y - premierPoint.getY());
			y = premierPoint.getY() + tailleFixe(y - premierPoint.getY(), x - premierPoint.getX());
		}
		return new double[] {x, y};
	}

	public double tailleFixe(double x, double y) {
		return x * 120 / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public double diagonale(double x, double y) {
		return signe(x) * (Math.abs(x) + Math.abs(y)) / 2;
	}

	public int signe(double x) {
		return x > 0 ? 1 : -1;
	}

	public boolean tailleSegmentMinimum(double x, double y, int i) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) < i;
	}

	public boolean tailleAxeMinimum(double a, double b, int i) {
		return Math.abs(a - b) < i;
	}
}