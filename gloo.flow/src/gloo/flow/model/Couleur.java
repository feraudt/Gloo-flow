package gloo.flow.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	ROUGE("rouge"),
	ORANGE("orange"),
	BLEU("bleu"),
	VERT("vert"),
	JAUNE("jaune");
	
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
	
	/**
	 * Constructeur d'une couleur qui permet d'indiquer la case de d�part et le second plot de chaque couleur.
	 * @param square
	 * @param end
	 */
	private Couleur(String couleur){
		int l_1 = 0;
		int l_2 = 0;
		int c_1 = 0;
		int c_2 = 0;
		try {
		      File myObj = new File("src/level.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        if (data.length()!=0 && (data.charAt(0) == couleur.charAt(0))) {
			        l_1 = Character.getNumericValue(data.charAt(7));
			        l_2 = Character.getNumericValue(data.charAt(11));
			        c_1	= Character.getNumericValue(data.charAt(9));
			        c_2 = Character.getNumericValue(data.charAt(13));
		        }
		      }
		      
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		Case square = new Case(l_1,c_1);
		Case end = new Case(l_2,c_2);
		tuyau = nouveauTuyau(square,end);
		plot_1 = new Plot(square);
		plot_2 = new Plot(end);
	}
	
	/**
	 * Permet de cr�er un tuyau � partir d'une couleur et de ses propri�t�s
	 * @param square
	 * @param end
	 * @return
	 */
	public Tuyau nouveauTuyau(Case square,Case end) {
		return new Tuyau(square,end);
	}
	
}
