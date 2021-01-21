package gloo.flow;

import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

public class Plateau {
	public int nbLignes;
	public int nbColonnes;
	
	/**
	 * Constructeur du plateau
	 * @param nbLignes Nombre de lignes du plateau
	 * @param nbColonnes Nombre de colonnes du plateau
	 */
	public Plateau(int nbLignes, int nbColonnes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
	}
	
	/**
	 * Renvoie la case voisine de celle pass�e en param�tre relativement � la direction voulue
	 * @param l
	 * @param c
	 * @param dir
	 * @return Case case voisine demand�e
	 */
	public Case getMaCaseVoisine(int l, int c, Direction dir) {
		return switch (dir) {
		case HAUT -> new Case(l-1,c);
		case BAS -> new  Case(l+1,c);
		case GAUCHE -> new Case(l,c-1);
		case DROITE -> new Case(l,c+1);
	};
	}
	
	/**
	 * Indique si le plateau est complet (jeu termin�)
	 * Si pour une couleur, la derniere case d'un tuyau ne correspond pas au second plot qu'il doit rejoindre, le jeu n'est pas termin�
	 * @return boolean true si le jeu est termin�, false sinon
	 */
	public boolean plateauComplet() {
		boolean complet = true;
		for (Couleur couleur: Couleur.class.getEnumConstants()) {
			if (couleur.tuyau.verifComplet() == false){
				complet = false;
			}
		}
		return complet;
	}
	 
}
