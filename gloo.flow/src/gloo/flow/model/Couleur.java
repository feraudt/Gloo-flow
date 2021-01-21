package gloo.flow.model;

import java.util.ArrayList;

import gloo.flow.Case;
import gloo.flow.Plot;
import gloo.flow.Tuyau;

/**
 * Énumérations des couleurs pour le jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public enum Couleur {
	ROUGE(new Case(0,0),new Case(4,1)),
	ORANGE(new Case(1,4), new Case(4,3)),
	BLEU(new Case(1,2),new Case(4,2)),
	VERT(new Case(0,2),new Case(3,1)),
	JAUNE(new Case(0,4),new Case(3,3));
	
	/*
	 * Il est IMPORTANT de noter qu'une énumération en Java est 
	 * aussi une classe, et peut donc avoir aussi des attributs, 
	 * des méthodes... Il est donc possible de respecter le
	 * modèle métier du jeu avec cette énumération.
	 * La seule contrainte est qu'il n'est pas possible de créer
	 * d'autres instances que celles nommées ci-dessus, ce qui
	 * implique en particulier qu'un éventuel constructeur doit
	 * être privé.
	 */
	public Tuyau tuyau;
	public Plot plot_1;
	public Plot plot_2;
	private Couleur(Case square, Case end){
		tuyau = nouveauTuyau(square,end);
		plot_1 = new Plot(square);
		plot_2 = new Plot(end);
	}
	
	public Tuyau nouveauTuyau(Case square,Case end) {
		return new Tuyau(square,end);
	}
	
	public int[] getPositionPlotDepartTuyau() {
		return this.tuyau.getCase();
	}

	public int[] getPositionSecondPlot() {
        return this.tuyau.getEnd();
	}
	public Tuyau getTuyau() {
		return this.tuyau;
	}
	public ArrayList<Direction> getDirections() {
        return this.tuyau.getDirection();
	}
	
	
}
