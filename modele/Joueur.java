package modele;

import java.io.Serializable;

public class Joueur implements Serializable {
	private static final long serialVersionUID = 40L;

	private final String nom;
	private final Couleur couleur;

	public Joueur(String nom, Couleur couleur) {
		super();
		this.nom = nom;
		this.couleur = couleur;
	}

	public String getNom() {
		return nom;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur joueur = (Joueur) obj;
			return nom.equals(joueur.nom) || couleur.equals(joueur.couleur);
		}
		return false;
	}
}
