package gloo.flow.control;

import java.util.ArrayList;

import gloo.flow.Case;
import gloo.flow.Plateau;
import gloo.flow.Tuyau;
import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

public class Controleur implements IControleur {
	public Couleur selected;
	public Case selectedSquare;
	public Plateau plateau = new Plateau(5,5);
	@Override
	public boolean selectionCase(int ligne, int colonne) {
        for( Couleur couleur : Couleur.class.getEnumConstants() ) {
        	int[] pos = getPositionPlotDepartTuyau(couleur);
        	if (pos[0]==ligne && pos[1]==colonne) {
        		selected = couleur;
        		selectedSquare = new Case(ligne,colonne);
        		return true;
        	}
        }
        return false;
	}

	@Override
	public boolean action(Direction direction) {
        Tuyau tuyau = selected.getTuyau();
        tuyau.modifier(direction);
        System.out.println("Test final -- " +plateau.plateauComplet2());
        return false;
	}

	public int getNbLignes() {
		return 5;
	}

	public int getNbColonnes() {
		return 5;
	}

	@Override
	public int[] getPositionPlotDepartTuyau(Couleur couleur) {
		return couleur.getPositionPlotDepartTuyau();
	}

	@Override
	public int[] getPositionSecondPlot(Couleur couleur) {
		return couleur.getPositionSecondPlot();
	}

	@Override
	public ArrayList<Direction> getDirections(Couleur couleur) {
		return couleur.getDirections();
	}

}
