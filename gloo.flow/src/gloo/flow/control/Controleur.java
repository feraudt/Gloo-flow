package gloo.flow.control;

import java.util.ArrayList;

import gloo.flow.Case;
import gloo.flow.Tuyau;
import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

public class Controleur implements IControleur {
	public Couleur selected;
	public Case selectedSquare;
	@Override
	public boolean selectionCase(int ligne, int colonne) {
        System.out.println("clic en l" + ligne + "c" + colonne);
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
        System.out.println("flèche " + direction.name());
        Tuyau tuyau = selected.getTuyau();
        tuyau.modifier(direction);
        return false;
	}

	@Override
	public int getNbLignes() {
		return 5;
	}

	@Override
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
