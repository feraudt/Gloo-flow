package gloo.flow.control;

import java.util.ArrayList;

import gloo.flow.Case;
import gloo.flow.Plateau;
import gloo.flow.Tuyau;
import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Controleur implements IControleur {
	public Couleur selected;
	public Case selectedSquare;
	public Plateau plateau = new Plateau(5,5);
	public int nbLignes;
	public int nbColonnes;
	
	public Controleur(int l,int c) {
		this.nbLignes = l ;
		this.nbColonnes = c; 
	}
	
	@Override
	public boolean selectionCase(int ligne, int colonne) {
        for( Couleur couleur : Couleur.class.getEnumConstants() ) {
        	int[] posDepart = getPositionPlotDepartTuyau(couleur);
        	int[] posArrivee = getPositionSecondPlot(couleur);
        	if ((posDepart[0]==ligne && posDepart[1]==colonne) || (posArrivee[0] == ligne && posArrivee[1] == colonne)) {
        		selected = couleur;
        		selectedSquare = new Case(posDepart[0],posDepart[1]);
        		return true;
        	}
        }
        return false;
	}

	@Override
	public boolean action(Direction direction) {
        Tuyau tuyau = selected.tuyau;
        tuyau.modifier(direction);
        if (plateau.plateauComplet()) {
        	System.out.println("Vous avez gagné !");
        	return true;
        } else {
        	return false;
        }
	}
	
	public int getNbLignes() {
		return this.nbLignes;
	}

	public int getNbColonnes() {
		return this.nbColonnes;
	}

	@Override
	public int[] getPositionPlotDepartTuyau(Couleur couleur) {
		return couleur.tuyau.getCase();
	}

	@Override
	public int[] getPositionSecondPlot(Couleur couleur) {
		return couleur.tuyau.getEnd();
	}

	@Override
	public ArrayList<Direction> getDirections(Couleur couleur) {
		return couleur.tuyau.directions;
	}

}
