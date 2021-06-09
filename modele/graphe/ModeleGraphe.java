package modele.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modele.DeepClone;
import modele.Joueur;
import modele.point.Point;
import modele.segment.Segment;

public class ModeleGraphe extends DeepClone {
	private static final long serialVersionUID = 51L;

	private String nom;

	private ArrayList<Point> points;
	private Map<String, Point> pointsSpeciaux;
	private ArrayList<Segment> segments;
	private ArrayList<Joueur> joueurs;

	private int joueurCourant;

	public ModeleGraphe(String nom) {
		this();
		this.nom = nom;
	}

	public ModeleGraphe() {
		this.nom = null;
		this.points = new ArrayList<>();
		this.pointsSpeciaux = new HashMap<>();
		this.segments = new ArrayList<>();
		this.joueurs = new ArrayList<>();
		this.joueurCourant = 0;
	}

	public boolean aUnPointClone(Point p) {
		for (Point p2 : points) {
			if (p.equals(p2))
				return true;
		}
		return false;
	}

	public boolean aUnSegmentClone(Segment s) {
		for (Segment s2 : segments) {
			if (s.equals(s2))
				return true;
		}
		return false;
	}

	public static ModeleGraphe creerGrapheAleatoire(int nbPoint, int nbSegment, double maxX, double maxY, int gap) {

		ModeleGraphe modeleGraphe = new ModeleGraphe();
		ArrayList<Point> points = modeleGraphe.points;
		ArrayList<Segment> segments = modeleGraphe.segments;

		double X = maxX - 30;
		double Y = maxY - 30;

		Point pointPrecedent;
		do {
			int i = (int) (Math.random() * (int) (X / gap));
			int j = (int) (Math.random() * (int) (Y / gap));
			pointPrecedent = new Point(i * gap + 30, j * gap + 30);
			// point = new Point(Math.random() * maxX, Math.random() * maxY);
		} while (modeleGraphe.aUnPointClone(pointPrecedent));
		points.add(pointPrecedent);
		
		for (int x = 1; x < nbPoint; x++) {
			Point point;
			do {
				int i = (int) (Math.random() * (int) (X / gap));
				int j = (int) (Math.random() * (int) (Y / gap));
				point = new Point(i * gap + 30, j * gap + 30);
				// point = new Point(Math.random() * maxX, Math.random() * maxY);
			} while (modeleGraphe.aUnPointClone(point));
			points.add(point);
			segments.add(new Segment(point, pointPrecedent));
			pointPrecedent = point;
		}
		segments.add(new Segment(points.get(0), pointPrecedent));

		for (int i = 0; i < nbSegment; i++) {
			Point point;
			Point point2;
			Segment segment;
			do {
				point = points.get((int) (Math.random() * nbPoint));
				point2 = points.get((int) (Math.random() * nbPoint));
				segment = new Segment(point, point2);
			} while (point.equals(point2) || modeleGraphe.aUnSegmentClone(segment));
			segments.add(segment);
		}

		return modeleGraphe;
	}
	
	public void joueurSuivant() {
		joueurCourant = (joueurCourant + 1) % getNbJoueurs();
	}
	
	public int getJoueurPrecedent() {
		return (joueurCourant + getNbJoueurs() - 1) % getNbJoueurs();
	}

	public void addPoint(Point point) {
		if (!points.contains(point))
			points.add(point);
	}

	public void addPointSpeciaux(String string, Point point) {
		if (!pointsSpeciaux.containsKey(string))
			pointsSpeciaux.put(string, point);
	}

	public void addSegment(Segment segment) {
		if (!segments.contains(segment))
			segments.add(segment);
	}

	public void addJoueur(Joueur joueur) {
		if (!joueurs.contains(joueur))
			joueurs.add(joueur);
	}

	public Point getPoint(int index) {
		return points.get(index);
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public Point getPointSpecial(String string) {
		return pointsSpeciaux.get(string);
	}

	public Map<String, Point> getPointsSpeciaux() {
		return pointsSpeciaux;
	}

	public Segment getSegment(int index) {
		return segments.get(index);
	}

	public ArrayList<Segment> getSegments() {
		return segments;
	}

	public boolean containsPoint(Point p) {
		return points.contains(p);
	}

	public boolean containsSegment(Segment s) {
		return segments.contains(s);
	}

	public boolean containsJoueur(Joueur j) {
		return joueurs.contains(j);
	}

	public Joueur getJoueur(int index) {
		return joueurs.get(index);
	}

	public int getJoueurCourant() {
		return joueurCourant;
	}

	public int getSizePoints() {
		return points.size();
	}

	public int getSizeSegments() {
		return segments.size();
	}

	public int getNbJoueurs() {
		return joueurs.size();
	}

	public void removePoint(Point p) {
		for (int i = segments.size() - 1; i >= 0; i--) {
			if (segments.get(i).getPoint1() == p || segments.get(i).getPoint2() == p)
				removeSegment(i);
		}
		points.remove(p);
		removePointSpeciaux(p);
	}

	public void removePoint(int nb) {
		removePoint(points.get(nb));
	}

	public void removePointSpeciaux(Point p) {
		while (pointsSpeciaux.values().remove(p))
			;
	}

	public void removePointSpeciaux(String key) {
		pointsSpeciaux.remove(key);
	}

	public void removeSegment(Segment s) {
		segments.remove(s);
	}

	public void removeSegment(int i) {
		segments.remove(i);
	}

	public void supprimerTout() {
		points.clear();
		segments.clear();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void clearJoueurs() {
		joueurs.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ModeleGraphe) {
			ModeleGraphe modeleGraphe = (ModeleGraphe) obj;
			return nom.equals(modeleGraphe.nom) && points.equals(modeleGraphe.points)
					&& pointsSpeciaux.equals(modeleGraphe.pointsSpeciaux) && segments.equals(modeleGraphe.segments)
					&& joueurs.equals(modeleGraphe.joueurs);
		}
		return false;
	}
}