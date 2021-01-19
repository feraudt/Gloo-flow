package gloo.flow;

import java.util.ArrayList;


import gloo.flow.model.Direction;

public class Tuyau {
	Case square = new Case(0,0);
	Case end = new Case(0,0);;
	ArrayList<Direction> directions = new ArrayList<Direction>();
	public Tuyau(Case square, Case end) {
		this.square = square;
		this.end = end;
	}
	
	public void modifier(Direction dir) {
		Case derniereCase = this.calculerDerniereCase(square, directions);
		Case caseVoisine = derniereCase.getCaseVoisine(dir);
		if (caseVoisine.accepteTuyau()) {
			directions.add(dir);
		}
	}
	
	public void ajouteCase(Case square) {
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
}
