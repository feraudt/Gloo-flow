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
	
	/**  Méthode appelée quand on tente d'étendre le tuyau sur une cette case
	 *  <ul>
	 *  <li> Si cette case contient le début d'un plot, la requête est refusée
	 * 	<li> Si cette case correspond à la fin d'un plot et que cette fin est différente de celle passée en paramètre, la requête est refusée
	 *  <li> Si cette case appartient déjà à un tuyau, la requête est refusée
	 *  </ul>
	 * @param Case Second plot du tuyau selectionné
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
