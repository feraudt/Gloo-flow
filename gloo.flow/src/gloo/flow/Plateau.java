package gloo.flow;

import java.util.ArrayList;

import gloo.flow.model.Couleur;
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
		return switch (dir) {
		case HAUT -> new Case(l-1,c);
		case BAS -> new  Case(l+1,c);
		case GAUCHE -> new Case(l,c-1);
		case DROITE -> new Case(l,c+1);
	};
	}
	
	public boolean plateauComplet() {
		boolean caseOccupee=false;
		for (int l=0;l<nbLignes;l++) {
			for (int c=0;c<nbColonnes;c++) {
				caseOccupee = false;
				for( Couleur couleur : Couleur.class.getEnumConstants() ) {
					int[] caseDepart = couleur.getPositionPlotDepartTuyau();
					int[] caseFin = couleur.getPositionSecondPlot(); 
					if (caseDepart[0] == l && caseDepart[1] == c) {
						System.out.println("Case depart" + "l"+caseDepart[0]+"c"+caseDepart[1]+" couleur: " + couleur);
						caseOccupee = true;
					} else if (caseFin[0] == l && caseFin[1] == c) {
						System.out.println("Case fin" + "l"+caseFin[0]+"c"+caseFin[1]+" couleur: " + couleur);
						caseOccupee = true;
					} else {
						ArrayList<Case> casesTuyau = couleur.getTuyau().cases;
						for (Case square:casesTuyau) {
							if (square.l == l && square.c== c) {
								caseOccupee = true;
							}
						}
					}
				}
				if (caseOccupee == false) {
					System.out.println("l"+l+"c"+c+" non occupée");
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean plateauComplet2() {
		boolean complet = true;
		for (Couleur couleur: Couleur.class.getEnumConstants()) {
			if (couleur.tuyau.verifComplet() == false){
				complet = false;
			}
		}
		return complet;
	}
	 
}
