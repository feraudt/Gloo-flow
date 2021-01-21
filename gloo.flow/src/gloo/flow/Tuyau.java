package gloo.flow;

import java.util.ArrayList;


import gloo.flow.model.Direction;

public class Tuyau {
	Case square = new Case(0,0);
	Case end = new Case(0,0);;
	public ArrayList<Direction> directions = new ArrayList<Direction>();
	public ArrayList<Case> cases = new ArrayList<Case>();
	
	/**
	 * Constructeur d'un tuyau caract�ris� par
	 * <ul>
	 * <li> La case correspondant � son plot de d�part
	 * <li> La case correspondant au second plot qu'il doit rejoindre
	 * <li> La liste des cases qu'il occupe (contient initialement sa case de d�part)
	 * </ul>
	 * @param square Case de d�part
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
	 * <li> Si on revient en arri�re (= Si l'avant derni�re case du tuyau correspond � la case voulue) : <br/>
	 * 		La derniere case est retir�e de la liste des cases du tuyau
	 * <li> Sinon, on v�rifie que la case d�sir�e est accessible : <br/>
	 * 		On ajoute le cas �ch�ant la case � la liste du tuyau
	 * </ul>
	 * @param dir Direction � donner au tuyau
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
	 * Ajoute une case � la liste des cases du tuyau
	 * @param square Case � ajouter
	 */
	public void ajouteCase(Case square) {
		this.cases.add(square);
	}
	
	/**
	 * Retire la derni�re case de la liste des cases du tuyau
	 */
	public void retireCase() {
		this.cases.remove(cases.size() - 1);
	}
	
	/**
	 * Renvoie les coordonn�es de la case de d�part sous le format ligne/colonne
	 * @return int[] {ligne, colonne)
	 */
	public int[] getCase() {
		return new int[] {square.l,square.c} ;
	}
	
	/**
	 * Renvoie les coordonn�es du second plot sous le format ligne/colonne
	 * @return int[] {ligne, colonne)
	 */	
	public int[] getEnd() {
		return new int[] {end.l, end.c};
	}
	
	/**
	 * Permet d'acceder � la liste des directions prises par le tuyau
	 * N�cessaire au trac� du tuyau
	 * @return int[] {ligne, colonne)
	 */
	public ArrayList<Direction> getDirection() {
		return directions;
	}
	
	/**
	 * Calcule la derni�re case occup�e par le tuyau � partir de la liste des directions utilis�es
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
	 * M�thode appel�e par le plateau pour v�rifier qu'un tuyau est complet
	 * Si la derni�re case occup�e par le tuyau correspond au second plot de ce tuyau, il est complet
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
