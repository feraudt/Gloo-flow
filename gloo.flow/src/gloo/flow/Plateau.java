package gloo.flow;

import gloo.flow.model.Direction;

public class Plateau {
	public int nbLignes;
	public int nbColonnes;
	public Plateau(int nbLignes, int nbColonnes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
	}
	public Plot getPlot(int l, int c) {
		return null;
	}
	
	public Case getMaCaseVoisine(int l, int c, Direction dir) {
		System.out.print("test2" + l + c + dir);
		return switch (dir) {
		case HAUT -> new Case(l-1,c);
		case BAS -> new  Case(l+1,c);
		case GAUCHE -> new Case(l,c-1);
		case DROITE -> new Case(l,c+1);
	};
	}
	
	public boolean plateauComplet() {
		return false;
	}
	 
}
