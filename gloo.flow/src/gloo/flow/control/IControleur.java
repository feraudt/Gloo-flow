package gloo.flow.control;

import java.util.ArrayList;

import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

/**
 * Interface du controleur pour le jeu Flow 
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public interface IControleur {

    /**
     * Méthode appellée par l'IHM quand le joueur clique sur une case.
     * 
     * <ul>
     * <li> Si cette case contient un plot :
     *   <ul>
     *   <li> si un tuyau de la même couleur que le plot existe, il
     *        est détruit ;
     *   </li>
     *   <li> un nouveau tuyau, de la couleur du plot, est créé, il prend
     *        comme point de départ ce plot et devient le tuyau courant ;
     *   </li>
     *   <li> la méthode retourne true ;
     *   </li>
     *   </ul>
     * </li>
     * <li> Sinon, il ne se passe rien et la méthode retourne false.
     * </li>
     * </ul>
     * @param ligne numéro de la ligne de la case sélectionnée (de 0 à 
     *              getNbLignes() - 1)
     * @param colonne numéro de la colonne de la case sélectionnée (de 0 à 
     *                getNbColonnes() - 1)
     * @return true si un plot est présent sur la case, false sinon.
     */
    boolean selectionCase( int ligne, int colonne );

    /**
     * Méthode appellée par l'IHM quand je joueur appuie sur l'une des
     * flèches du clavier.
     * 
     * S'il n'y a pas de tuyau courant, rien ne se passe.
     * S'il y a un tuyau courant, celui-ci doit s'agrandir, si
     * il le peut, dans la direction indiquée en argument.
     * 
     * @param direction direction de la progression demandée par le joueur
     * @return true si le plateau est terminé, false sinon
     */
    boolean action( Direction direction );

    /**
     * Méthode appellée par l'IHM pour connaître le nombre de
     * lignes du plateau courant.
     * 
     * @return le nombre de lignes du plateau à afficher
     */
    int getNbLignes();

    /**
     * Méthode appellée par l'IHM pour connaître le nombre de
     * colonnes du plateau courant.
     * 
     * @return le nombre de colonnes du plateau à afficher
     */
    int getNbColonnes();

    /**
     * Méthode appellée par l'IHM pour obtenir la position de 
     * départ du tuyau d'une couleur donnée.
     * 
     * @param couleur identifie le tuyau demandé via sa couleur 
     * @return un tableau de 2 entiers [ligne, colonne] donnant la
     *         position de départ du tuyau associé à la couleur
     *         indiquée. S'il n'y a pas encore de tuyau pour cette 
     *         couleur, la position de l'un des plots de cette 
     *         couleur.
     */
    int[] getPositionPlotDepartTuyau( Couleur couleur );

    /**
     * Méthode appellée par l'IHM pour obtenir le second plot
     * d'une couleur donnée.
     * 
     * @param couleur identifie la couleur du plot cherché 
     * @return un tableau de 2 entiers [ligne, colonne] donnant la
     *         position du second plot de cette couleur (la 
     *         première est retournée par {@link #getPositionPlotDepartTuyau(Couleur)
     *         getPositionPlotDepartTuyau(Couleur)}.
     */
    int[] getPositionSecondPlot( Couleur couleur );

    /**
     * Méthode appellée par l'IHM pour obtenir le cheminement d'un tuyau
     * sur le plateau.
     * 
     * @param couleur identifie la couleur du tuyau cherché. 
     * @return un tableau de valeurs de l'énumération Direction.
     *         Le point de départ du chemin est celui donné par 
     *         {@link #getPositionPlotDepartTuyau(Couleur)
     *         getPositionPlotDepartTuyau(Couleur)}.
     */
    ArrayList<Direction> getDirections( Couleur couleur );

}

