package gloo.flow;

import java.util.ArrayList;


import gloo.flow.model.Direction;

public class Tuyau {
	Case square = new Case(0,0);
	Case end = new Case(0,0);;
	public ArrayList<Direction> directions = new ArrayList<Direction>();
	public ArrayList<Case> cases = new ArrayList<Case>();
	
	/**
	 * Constructeur d'un tuyau caractérisé par
	 * <ul>
	 * <li> La case correspondant à son plot de départ
	 * <li> La case correspondant au second plot qu'il doit rejoindre
	 * <li> La liste des cases qu'il occupe (contient initialement sa case de départ)
	 * </ul>
	 * @param square Case de départ
	 * @param end Case du second plot
	 */
	public Tuyau(Case square, Case end) {
		cases.add(square);
		this.square = square;
		this.end = end;
	}
	
	/**
	 * Permet de modifier un tuyau en indiquant la direction qu'il doit prendre
	 * <ul>
	 * <li> Si on revient en arrière (= Si l'avant dernière case du tuyau correspond à la case voulue) : <br/>
	 * 		La derniere case est retirée de la liste des cases du tuyau
	 * <li> Sinon, on vérifie que la case désirée est accessible : <br/>
	 * 		On ajoute le cas échéant la case à la liste du tuyau
	 * </ul>
	 * @param dir Direction à donner au tuyau
	 */
	public void modifier(Direction dir) {
		Case derniereCase = cases.get(cases.size() - 1);
		Case caseVoisine = derniereCase.getCaseVoisine(dir);
		if (cases.size()>1 && caseVoisine.c == cases.get(cases.size()-2).c && caseVoisine.l == cases.get(cases.size()-2).l ) {
			this.directions.remove(this.directions.size()-1);
			retireCase();
		} else if (caseVoisine.accepteTuyau(end)) {
			this.directions.add(dir);
			ajouteCase(caseVoisine);
		}
	}
	
	/**
	 * Ajoute une case à la liste des cases du tuyau
	 * @param square Case à ajouter
	 */
	public void ajouteCase(Case square) {
		this.cases.add(square);
	}
	
	/**
	 * Retire la dernière case de la liste des cases du tuyau
	 */
	public void retireCase() {
		this.cases.remove(cases.size() - 1);
	}
	
	/**
	 * Renvoie les coordonnées de la case de départ sous le format ligne/colonne
	 * @return int[] {ligne, colonne)
	 */
	public int[] getCase() {
		return new int[] {square.l,square.c} ;
	}
	
	/**
	 * Renvoie les coordonnées du second plot sous le format ligne/colonne
	 * @return int[] {ligne, colonne)
	 */	
	public int[] getEnd() {
		return new int[] {end.l, end.c};
	}
	
	/**
	 * Permet d'acceder à la liste des directions prises par le tuyau
	 * Nécessaire au tracé du tuyau
	 * @return int[] {ligne, colonne)
	 */
	public ArrayList<Direction> getDirection() {
		return directions;
	}
	
	/**
	 * Calcule la dernière case occupée par le tuyau à partir de la liste des directions utilisées
	 * @return int[] {ligne, colonne)
	 */
	public Case calculerDerniereCase(Case square,ArrayList<Direction> directions) {
		int c = square.c;
		int l = square.l;
		for (Direction dir : directions) {
			if (dir == Direction.HAUT){
				l -= 1;
			} else if (dir == Direction.BAS) {
				l += 1;
			} else if (dir == Direction.GAUCHE) {
				c -= 1;
			} else if (dir == Direction.DROITE) {
				c += 1;
			}
		}
		return new Case(l,c);
	}
	
	/**
	 * Méthode appelée par le plateau pour vérifier qu'un tuyau est complet
	 * Si la dernière case occupée par le tuyau correspond au second plot de ce tuyau, il est complet
	 * @return boolean
	 */
	public boolean verifComplet() {
		Case derniereCase = this.cases.get(cases.size() -1);
		if (this.end.l == derniereCase.l && this.end.c == derniereCase.c) {
			return true;
		}
		return false;
	}
}
