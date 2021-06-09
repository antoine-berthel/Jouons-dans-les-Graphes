package controleur;

import Jeux.Jeu;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import modele.point.Point;
import modele.point.PointCouleur;
import modele.segment.SegmentCouleur;
import vue.Vue;

public class ControleurJeu extends ControleurRetour {

	protected Jeu jeu;
	private Object action;

	public ControleurJeu(Vue vue, Jeu jeu) {
		super(vue);
		this.jeu = jeu;
		jeu.start();
	}

	public synchronized void setApplique(Object action) {
		this.action = action;
		notify();
	}

	public void applique(PointCouleur p) {
		p.setCouleur(modele.getJoueur(modele.getJoueurCourant()).getCouleur());
		modele.joueurSuivant();
		vue.update();
	}

	public void applique(PointCouleur p, double x, double y) {
		p.setX(x);
		p.setY(y);
		modele.joueurSuivant();
		vue.update();
	}

	public void applique(SegmentCouleur s) {
		s.setCouleur(modele.getJoueur(modele.getJoueurCourant()).getCouleur());
		modele.joueurSuivant();
		vue.update();
	}

	public void appliqueDep(Circle cercle) {
		Dragboard db = cercle.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("");
		db.setContent(content);
	}

	@Override
	public synchronized void handle(InputEvent event) {
		super.handle(event);
		Object source = event.getSource();
		if (event instanceof DragEvent) {
			eventDrag((DragEvent) event, (Pane) source);
		} else if (event instanceof MouseEvent) {
			eventMouse((MouseEvent) event, source);
		}
	}

	@Override
	public synchronized void exit() {
		jeu.interrupt();
		notifyAll();
		super.exit();
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
		action = null;
		jeu.setEvent(event);
		try {
			wait();
			if (action != null) {
				jeu.applique(action);
			}
		} catch (InterruptedException e) {
		}
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
	
	public boolean tailleSegmentMinimum(double x, double y, int i) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) < i;
	}
}
