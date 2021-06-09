package Jeux;

import java.util.ArrayList;

import controleur.ControleurJeu;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import modele.point.Point;
import modele.point.PointCouleur;
import modele.regle.ModeleRegle;
import modele.segment.Segment;
import modele.segment.SegmentCouleur;
import vue.Vue;

public class JeuModifie extends Jeu {

	public JeuModifie(Vue vue) {
		super(vue);
	}

	public synchronized boolean tour(int nb) throws InterruptedException, RuntimeException {
		wait();
		Object source = event.getSource();
		ModeleRegle regle = modele.getRegleCourant();
		if (event.getEventType() == MouseEvent.DRAG_DETECTED) {
			if (regle.DeplacementAutorise.get()) {
				if (source instanceof Circle && vue.getCercles().containsKey(source)) {
					((ControleurJeu) vue.getControleur()).setApplique((Circle) source);
					return false;
				}
			}
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			if (regle.ClickAutorise.get()) {
				if (source instanceof Circle && vue.getCercles().containsKey(source)) {
					PointCouleur point = (PointCouleur) (vue.getCercles().get(source));
					if (checkRegles(point)) {
						((ControleurJeu) vue.getControleur()).setApplique(point);
						return true;
					}
				}
			}
		} else {
			throw new RuntimeException("mauvait event");
		}
		return false;
	}

	public boolean checkDebut() {
		return true;
	}

	public boolean checkRegles(Point p) {
		ModeleRegle regle = modele.getRegleCourant();
		if (regle.JouerAcoteSoit.get() != null) {
			if (regle.JouerAcoteSoit.get()) {
				if (!regles.jouerAcoteSoit((PointCouleur) p)) {
					return false;
				}
			} else {
				if (regles.jouerAcoteSoit((PointCouleur) p)) {
					return false;
				}
			}
		}
		if (regle.JouerAcoteEnnemi.get() != null) {
			if (regle.JouerAcoteEnnemi.get()) {
				if (!regles.jouerAcoteEnnemi((PointCouleur) p)) {
					return false;
				}
			} else {
				if (regles.jouerAcoteEnnemi((PointCouleur) p)) {
					return false;
				}
			}
		}
		if (regle.JouerSurEnnemi.get() != null) {
			if (!regle.JouerSurEnnemi.get()) {
				if (regles.jouerSurEnnemi((PointCouleur) p)) {
					return false;
				}
			}
		}
		if (regle.EstBlanc.get() != null) {
			if (regle.EstBlanc.get()) {
				if (!regles.estBlanc((PointCouleur) p)) {
					return false;

				}
			} else {
				if (regles.estBlanc((PointCouleur) p)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkFin() {
		ModeleRegle regle = modele.getRegleCourant();
		if (regle.FinHex.get()) {
			ArrayList<Point> point = new ArrayList<>();
			if (regles.estLie(modele.getPointSpecial("depart0"), point,
					this.modele.getPointSpecial("arrive0"))) {
				return true;
			}
			point.clear();
			if (regles.estLie(modele.getPointSpecial("depart1"), point,
					modele.getPointSpecial("arrive1"))) {
				return true;
			}
			return false;
		}
		if (regle.FinDeplacement.get()) {
			for (Segment s : modele.getSegments()) {
				for (Segment seg : modele.getSegments()) {
					if (!s.sommetCommun(seg) && regles.seCroise(s, seg)) {
						return false;
					}
				}
			}
			return true;
		}
		for (Point p : modele.getPoints()) {
			if (checkRegles(p)) {
				return false;
			}
		}
		return true;
	}

	public void applique(Object o) {
		ControleurJeu c = (ControleurJeu) vue.getControleur();
		if (o instanceof PointCouleur) {
			c.applique((PointCouleur) o);
		} else if (o instanceof SegmentCouleur) {
			c.applique((SegmentCouleur) o);
		} else if (o instanceof Circle) {
			c.appliqueDep((Circle) o);
		}
	}
}
