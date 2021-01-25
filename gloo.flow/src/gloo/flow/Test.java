package gloo.flow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import gloo.flow.control.Controleur;
import gloo.flow.hmi.Fenetre;

/**
 * Programme de test du controleur bouchon du jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public class Test implements Runnable {

	public static void main( String[] args ) {
        SwingUtilities.invokeLater( new Test() );
	}

    @Override
    public void run() {
    	int nbLignes = 0;
    	int nbColonnes = 0;
		try {
		      File myObj = new File("src/level.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        if (data.length()!=0) {
			        if (data.charAt(0)=='l') {
			        	nbLignes = Integer.parseInt((data.substring(2)));
			        }
			        if (data.charAt(0)=='c') {
			        	nbColonnes = Integer.parseInt((data.substring(2)));
			        }
		        }
		      }
		      
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
        new Fenetre( new Controleur(nbLignes,nbColonnes) );
    }
}
