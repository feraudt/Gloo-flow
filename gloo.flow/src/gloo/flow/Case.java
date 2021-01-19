package gloo.flow;

import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

public class Case {
	int l;
	int c;
	public Case(int l,int c) {
		this.l = l;
		this.c = c;
	}
	public Plot getPlot() {
		return null;
	}
	public Case getCaseVoisine(Direction dir) {
		return switch (dir) {
			case HAUT -> new Case(l-1,c);
			case BAS -> new  Case(l+1,c);
			case GAUCHE -> new Case(l,c-1);
			case DROITE -> new Case(l,c+1);
		};
	}
	public boolean accepteTuyau() {
		for( Couleur couleur : Couleur.class.getEnumConstants() ) {
			int[] start = couleur.getPositionPlotDepartTuyau();
			if (this.l == start[0]  && this.c == start[1]) {
				return false;
			}
			for (Direction dir:couleur.getDirections()) {
				if (dir == Direction.HAUT){
					start[0] -= 1;
				} else if (dir == Direction.BAS) {
					start[0] += 1;
				} else if (dir == Direction.GAUCHE) {
					start[1] -= 1;
				} else if (dir == Direction.DROITE) {
					start[1] += 1;
				}
				if (this.l == start[0]  && this.c == start[1]){
					return false;
				}
			}
		}
		return true;
	}
	public boolean valideFinJeu() {
		return false;
	}
}
