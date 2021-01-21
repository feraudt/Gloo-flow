package gloo.flow.hmi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import gloo.flow.control.IControleur;
import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

/**
 * Panneau de l'IHM pour le jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
@SuppressWarnings( "serial" )
public class Panneau extends JPanel implements MouseListener {

    private static final float EPAISSEUR = 4;
    private boolean premierAffichage = true;
    private IControleur controleur;
    private static int nbLignes;
    private static int nbColonnes;
    private int coteCase;
    private int diametrePlot;
    private int demiRayon;
    private int largeurTuyau;
    private int arcRoundRect;
    private int[] selection;

    public Panneau( IControleur controleur ) {
        this.controleur = controleur;
        this.addMouseListener( this );
    }

    private void calculeParametres() {
        nbLignes = controleur.getNbLignes();
        int cote_l = getSize().height / nbLignes;
        nbColonnes = controleur.getNbColonnes();
        int cote_c = getSize().width / nbColonnes;
        coteCase = cote_l < cote_c ? cote_l : cote_c;
        diametrePlot = coteCase * 2 / 3;
        demiRayon = diametrePlot / 4;
        largeurTuyau = coteCase / 4;
        arcRoundRect = coteCase / 4;
        premierAffichage = false;
    }

    public void paint( Graphics g ) {
    	super.paint(g);
        if( premierAffichage ) calculeParametres();

        // Le côté métier raisonne en [ligne, colonne]
        // Le côté IHM raisonne en [x, y]
        // Donc x <=> colonne et y <=> ligne
        for( int i = 0; i <= nbLignes; ++i ) {
            g.drawLine( 0, i * coteCase, nbColonnes * coteCase, i * coteCase );
        }
        for( int j = 0; j <= nbColonnes; ++j ) {
            g.drawLine( j * coteCase, 0, j * coteCase, nbLignes * coteCase );
        }

        for( Couleur couleur : Couleur.class.getEnumConstants() ) {
            setCouleurGraphique( g, couleur );
            // Affichage du premier plot
            int[] posDepart = controleur.getPositionPlotDepartTuyau( couleur );
            int[] posArrivee = controleur.getPositionSecondPlot( couleur );
            g.fillOval( posDepart[1] * coteCase + demiRayon,
                    posDepart[0] * coteCase + demiRayon,
                    diametrePlot, diametrePlot );
            // Décoration du plot s'il est sélectionné
            if( Arrays.equals( posDepart, selection ) || Arrays.equals(posArrivee, selection)) {
                Color c = g.getColor();
                g.setColor( Color.BLACK );
                Graphics2D g2 = ( Graphics2D ) g;
                Stroke s = g2.getStroke();
                g2.setStroke( new BasicStroke( EPAISSEUR ));
                g.drawOval( posDepart[1] * coteCase + demiRayon,
                        posDepart[0] * coteCase + demiRayon,
                        diametrePlot, diametrePlot );
                g2.setStroke( s );
                g.setColor( c );
            }
            // Affichage de l'éventuel tuyau partant de ce plot
            paintDirections( g, posDepart, controleur.getDirections( couleur ));
            // Affichage du second plot
            g.fillOval( posArrivee[1] * coteCase + demiRayon,
                    posArrivee[0] * coteCase + demiRayon,
                    diametrePlot, diametrePlot );
        }
    }

    private void paintDirections( Graphics g, int[] posDepart, ArrayList<Direction> directions ) {
        // Ici :
        // x <=> posDepart[1] et y <=> posDepart[0]
        int x0 = posDepart[1] * coteCase + coteCase / 2 - largeurTuyau / 2;
        int y0 = posDepart[0] * coteCase + coteCase / 2 - largeurTuyau / 2;
        for( Direction dir : directions ) {
            int w = largeurTuyau;
            int h = largeurTuyau;
            int x1 = x0;
            int y1 = y0;
            switch( dir ) {
            case HAUT:
                y0 -= coteCase;
                y1 -= coteCase;
                h += coteCase;
                break;
            case BAS:
                h += coteCase;
                y1 += coteCase;
                break;
            case GAUCHE:
                x0 -= coteCase;
                x1 -= coteCase;
                w += coteCase;
                break;
            case DROITE:
                w += coteCase;
                x1 += coteCase;
                break;
            }
            g.fillRoundRect( x0, y0, w, h, arcRoundRect, arcRoundRect );
            x0 = x1;
            y0 = y1;
        }
    }

    private void setCouleurGraphique( Graphics g, Couleur c ) {
        g.setColor( switch( c ) {
            case ROUGE -> Color.RED;
            case ORANGE -> Color.ORANGE;
            case BLEU -> Color.BLUE;
            case VERT -> Color.GREEN;
            case JAUNE -> Color.YELLOW;
        } );
    }

    @Override
    public void mouseClicked( MouseEvent e ) {
        if( controleur.selectionCase( e.getPoint().y / coteCase, e.getPoint().x / coteCase )) {
            selection = new int[] { e.getPoint().y / coteCase, e.getPoint().x / coteCase };
        }        
        repaint();
    }
    public static int getNbLignes() {
    	return nbLignes;
    }
    
    public static int getNbColonnes() {
    	return nbColonnes;
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        // nothing
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
        // nothing
    }

    @Override
    public void mouseEntered( MouseEvent e ) {
        // nothing
    }

    @Override
    public void mouseExited( MouseEvent e ) {
        // nothing
    }
}
