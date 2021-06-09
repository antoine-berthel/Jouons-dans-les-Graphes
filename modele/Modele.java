package modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import modele.graphe.ModeleGraphe;
import modele.point.Point;
import modele.regle.ModeleRegle;
import modele.segment.Segment;

public class Modele implements Serializable {
	private static final long serialVersionUID = 50L;

	private ArrayList<ModeleGraphe> graphesPredefinis;
	private ArrayList<ModeleGraphe> graphesLocal;
	private ArrayList<ModeleRegle> reglesPredefinis;
	private ArrayList<ModeleRegle> reglesLocal;

	private ModeleGraphe grapheCourant;
	private ModeleRegle regleCourant;

	public Modele() {
		graphesPredefinis = new ArrayList<>();
		graphesLocal = new ArrayList<>();
		reglesPredefinis = new ArrayList<>();
		reglesLocal = new ArrayList<>();

		grapheCourant = null;
		regleCourant = null;
	}

	public void resetCourant() {
		grapheCourant = null;
		regleCourant = null;
	}

	public void sauvegardeGraphe() {
		graphesLocal.add((ModeleGraphe) grapheCourant.clone());
	}

	public void sauvegardeRegle() {
		reglesLocal.add((ModeleRegle) regleCourant.clone());
	}

	public void exportModele() throws IOException {
		String fileName = "modele.ser";
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}

	public static Modele importModele() throws IOException, ClassNotFoundException {
		String fileName = "modele.ser";
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Modele m = (Modele) ois.readObject();
		ois.close();
		return m;
	}

	public void setGrapheNom(String nom) {
		grapheCourant.setNom(nom);
	}

	public void setRegleNom(String nom) {
		regleCourant.setNom(nom);
	}

	public ArrayList<ModeleGraphe> getGraphesPredefinis() {
		return graphesPredefinis;
	}

	public ModeleGraphe getGraphesPredefinis(int i) {
		return graphesPredefinis.get(i);
	}

	public void addGraphesPredefinis(ModeleGraphe graphe) {
		graphesPredefinis.add(graphe);
	}

	public ArrayList<ModeleGraphe> getGraphesLocal() {
		return graphesLocal;
	}

	public ModeleGraphe getGraphesLocal(int i) {
		return graphesLocal.get(i);
	}

	public void addGraphesLocal(ModeleGraphe graphe) {
		graphesLocal.add(graphe);
	}

	public ArrayList<ModeleRegle> getReglesPredefinis() {
		return reglesPredefinis;
	}

	public ModeleRegle getReglesPredefinis(int i) {
		return reglesPredefinis.get(i);
	}

	public void addReglesPredefinis(ModeleRegle regle) {
		reglesPredefinis.add(regle);
	}

	public ArrayList<ModeleRegle> getReglesLocal() {
		return reglesLocal;
	}

	public ModeleRegle getReglesLocal(int i) {
		return reglesLocal.get(i);
	}

	public void addReglesLocal(ModeleRegle regle) {
		reglesLocal.add(regle);
	}

	public ModeleGraphe getGrapheCourant() {
		return grapheCourant;
	}

	public void setGrapheCourant(ModeleGraphe grapheCourant) {
		this.grapheCourant = (ModeleGraphe) grapheCourant.clone();
	}

	public ModeleRegle getRegleCourant() {
		return regleCourant;
	}

	public void setRegleCourant(ModeleRegle regleCourant) {
		this.regleCourant = (ModeleRegle) regleCourant.clone();
	}

	public void joueurSuivant() {
		this.grapheCourant.joueurSuivant();
	}

	public int getJoueurPrecedent() {
		return this.grapheCourant.getJoueurPrecedent();
	}

	public void addPoint(Point point) {
		grapheCourant.addPoint(point);
	}

	public void addPointSpeciaux(String string, Point point) {
		grapheCourant.addPointSpeciaux(string, point);
	}

	public void addSegment(Segment segment) {
		grapheCourant.addSegment(segment);
	}

	public void addJoueur(Joueur joueur) {
		grapheCourant.addJoueur(joueur);
	}

	public Point getPoint(int index) {
		return grapheCourant.getPoint(index);
	}

	public ArrayList<Point> getPoints() {
		return grapheCourant.getPoints();
	}

	public Point getPointSpecial(String string) {
		return grapheCourant.getPointSpecial(string);
	}

	public Map<String, Point> getPointsSpeciaux() {
		return grapheCourant.getPointsSpeciaux();
	}

	public Segment getSegment(int index) {
		return grapheCourant.getSegment(index);
	}

	public ArrayList<Segment> getSegments() {
		return grapheCourant.getSegments();
	}

	public boolean containsPoint(Point p) {
		return grapheCourant.containsPoint(p);
	}

	public boolean containsSegment(Segment s) {
		return grapheCourant.containsSegment(s);
	}

	public boolean containsJoueur(Joueur j) {
		return grapheCourant.containsJoueur(j);
	}

	public Joueur getJoueur(int index) {
		return grapheCourant.getJoueur(index);
	}

	public int getJoueurCourant() {
		return grapheCourant.getJoueurCourant();
	}

	public int getSizePoints() {
		return grapheCourant.getSizePoints();
	}

	public int getSizeSegments() {
		return grapheCourant.getSizeSegments();
	}

	public int getNbJoueurs() {
		return grapheCourant.getNbJoueurs();
	}

	public void removePoint(Point p) {
		grapheCourant.removePoint(p);
	}

	public void removePoint(int nb) {
		grapheCourant.removePoint(nb);
	}

	public void removePointSpeciaux(Point p) {
		grapheCourant.removePointSpeciaux(p);
	}

	public void removePointSpeciaux(String key) {
		grapheCourant.removePointSpeciaux(key);
	}

	public void removeSegment(Segment s) {
		grapheCourant.removeSegment(s);
	}

	public void removeSegment(int i) {
		grapheCourant.removeSegment(i);
	}

	public void supprimerTout() {
		grapheCourant.supprimerTout();
	}

	public void clearJoueurs() {
		grapheCourant.clearJoueurs();
	}

}
