package Jeux;

import java.util.ArrayList;

import modele.Couleur;
import modele.Joueur;
import modele.Modele;
import modele.point.Point;
import modele.point.PointCouleur;
import modele.segment.Segment;

public class Regles {
	
	public Modele modele;

	public Regles(Modele modele) {
		this.modele = modele;
	}

	public boolean jouerAcoteSoit(PointCouleur p) {
		Joueur j = modele.getJoueur(modele.getJoueurCourant());
		for (int i = 0; i < modele.getSizeSegments(); i++) {
			Point v = modele.getSegment(i).getVoisin(p);
			if (v instanceof PointCouleur) {
				PointCouleur pc = (PointCouleur) v;
				if (pc.getCouleur().equals(j.getCouleur()) && p.getCouleur().equals(Couleur.BLANC)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean jouerAcoteEnnemi(PointCouleur p) {
		Joueur j = modele.getJoueur(modele.getJoueurCourant());
		for (int i = 0; i < modele.getSizeSegments(); i++) {
			Point v = modele.getSegment(i).getVoisin(p);
			if (v instanceof PointCouleur) {
				PointCouleur pc = (PointCouleur) v;
				if (!pc.getCouleur().equals(j.getCouleur()) && !pc.getCouleur().equals(Couleur.BLANC)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean jouerSurEnnemi(PointCouleur p) {
		Joueur j = modele.getJoueur(modele.getJoueurCourant());
		return !p.getCouleur().equals(j.getCouleur()) && !estBlanc(p);
	}

	public boolean estBlanc(PointCouleur p) {
		return p.getCouleur().equals(Couleur.BLANC);
	}

	public boolean allAround(Point p) {
		Joueur j = modele.getJoueur(modele.getJoueurCourant());
		for (int i = 0; i < modele.getSizeSegments(); i++) {
			Point v = modele.getSegment(i).getVoisin(p);
			if (v != null) {
				if (v != null && v instanceof PointCouleur) {
					PointCouleur pc = (PointCouleur) v;
					if (!pc.getCouleur().equals(j.getCouleur())) {
						return false;
					}
				} else if (v != null) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean estLie(Point p, ArrayList<Point> point, Point depart) {
		point.add(p);
		for (Segment s : modele.getSegments()) {
			Point voisin = s.getVoisin(p);
			if(voisin instanceof PointCouleur) {
				if(((PointCouleur) voisin).getCouleur().equals(((PointCouleur) depart).getCouleur())) {
					if (voisin == depart) {
						return true;
					}
					if (!point.contains(voisin)) {
						if (estLie(voisin, point, depart))
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean estColoriable(){
		for(Point p : modele.getPoints()) {
			if(!(p instanceof PointCouleur)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean seCroise(Segment s1, Segment s2){
		return s1.toLine2D().intersectsLine(s2.toLine2D());
		
	}
}
