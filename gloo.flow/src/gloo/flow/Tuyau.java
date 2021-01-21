package gloo.flow;

import java.util.ArrayList;


import gloo.flow.model.Direction;

public class Tuyau {
	Case square = new Case(0,0);
	Case end = new Case(0,0);;
	ArrayList<Direction> directions = new ArrayList<Direction>();
	public ArrayList<Case> cases = new ArrayList<Case>();
	public Tuyau(Case square, Case end) {
		cases.add(square);
		this.square = square;
		this.end = end;
	}
	
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
	
	public void ajouteCase(Case square) {
		this.cases.add(square);
	}
	
	public void retireCase() {
		this.cases.remove(cases.size() - 1);
	}
	
	public int[] getCase() {
		return new int[] {square.l,square.c} ;
	}
	public int[] getEnd() {
		return new int[] {end.l, end.c};
	}
	public ArrayList<Direction> getDirection() {
		return directions;
	}
	
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
	
	public boolean verifComplet() {
		Case derniereCase = this.cases.get(cases.size() -1);
		if (this.end.l == derniereCase.l && this.end.c == derniereCase.c) {
			return true;
		}
		return false;
	}
}
