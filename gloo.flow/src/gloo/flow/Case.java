package gloo.flow;

import gloo.flow.hmi.Panneau;
import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

public class Case {
	Plateau plateau = new Plateau(5,5);
	public int l;
	public int c;
	
	public Case(int l,int c) {
		this.l = l;
		this.c = c;
	}
	
	/** Permet de demander au plateau sa case voisine en fontion de la direction voulue */
	public Case getCaseVoisine(Direction dir) {
		return plateau.getMaCaseVoisine(this.l,this.c,dir);
	}
	
	/**  M�thode appel�e quand on tente d'�tendre le tuyau sur une cette case
	 *  <ul>
	 *  <li> Si cette case contient le d�but d'un plot, la requ�te est refus�e
	 * 	<li> Si cette case correspond � la fin d'un plot et que cette fin est diff�rente de celle pass�e en param�tre, la requ�te est refus�e
	 *  <li> Si cette case appartient d�j� � un tuyau, la requ�te est refus�e
	 *  </ul>
	 * @param Case Second plot du tuyau selectionn�
	 */
	public boolean accepteTuyau(Case fin) {
		int maxl = Panneau.getNbLignes();
		int maxc = Panneau.getNbColonnes();
		for( Couleur couleur : Couleur.class.getEnumConstants() ) {
			int[] start = couleur.tuyau.getCase();
			int[] end = couleur.tuyau.getEnd();
			if ((this.l == start[0]  && this.c == start[1])) {
				return false;	
			}
			
			if ((fin.l == end[0] && fin.c == end[1]) == false){
				if (this.l == end[0]  && this.c == end[1]) {
					return false;
				}
			}
			for (Direction dir:couleur.tuyau.directions) {
				if (dir == Direction.HAUT){
					start[0] -= 1;
				} else if (dir == Direction.BAS) {
					start[0] += 1;
				} else if (dir == Direction.GAUCHE) {
					start[1] -= 1;
				} else if (dir == Direction.DROITE) {
					start[1] += 1;
				}
				if (this.l == start[0]  && this.c == start[1] || this.l < 0 || this.c < 0 || this.l >= maxl || this.c >= maxc ) {
					return false;
				}
			}
		}
		return true;
	}
}
