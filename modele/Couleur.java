package modele;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class Couleur implements Serializable {
	private static final long serialVersionUID = 30L;

	private double rouge;
	private double vert;
	private double bleu;

	public static final Couleur BLANC = new Couleur(1, 1, 1);
	public static final Couleur ROUGE = new Couleur(1, 0, 0);
	public static final Couleur VERT = new Couleur(0, 1, 0);
	public static final Couleur BLEU = new Couleur(0, 0, 1);
	public static final Couleur NOIR = new Couleur(0, 0, 0);

	public Couleur(double rouge, double vert, double bleu) {
		this.rouge = rouge;
		this.vert = vert;
		this.bleu = bleu;
	}

	public Color toColor() {
		return new Color(rouge, vert, bleu, 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Couleur) {
			Couleur couleur = (Couleur) obj;
			return rouge == couleur.rouge && vert == couleur.vert && bleu == couleur.bleu;
		}
		return false;
	}

	public double getRouge() {
		return rouge;
	}

	public void setRouge(double rouge) {
		this.rouge = rouge;
	}

	public double getVert() {
		return vert;
	}

	public void setVert(double vert) {
		this.vert = vert;
	}

	public double getBleu() {
		return bleu;
	}

	public void setBleu(double bleu) {
		this.bleu = bleu;
	}

}
