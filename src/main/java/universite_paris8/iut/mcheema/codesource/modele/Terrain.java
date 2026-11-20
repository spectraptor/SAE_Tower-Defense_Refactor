package universite_paris8.iut.mcheema.codesource.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * La classe Terrain détient les différents terrains de jeux, sous forme de tableaux.
 * Chaque niveau correspond à un terrain différent, et chaque lettre représente une texture à représenter
 */
public class Terrain {
    public final static int TAILLE_TUILLE = 32;
    private int niveauTerrain;
    private char[][] terrainDeJeu;

    public Terrain(int levelTerrain) {
        this.niveauTerrain = levelTerrain;
        this.initialiseTerrain();
    }

    public String avoirCheminTerrain() {
        return "src/main/resources/universite_paris8/iut/mcheema/codesource/terrains/terrain" + this.niveauTerrain + ".txt";
    }


    public ArrayList<String> lireLigneFichier(String chemin) {
        ArrayList<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lignes;
    }

    public char[][] construireGrilleTerrain(ArrayList<String> lignesTerrain) {

        char[][] tab = new char[lignesTerrain.size()][];
        for (int i = 0; i < lignesTerrain.size(); i++) {
            tab[i] = lignesTerrain.get(i).toCharArray();
        }

        return tab;
    }

    public void initialiseTerrain() {
        String cheminTerrain = this.avoirCheminTerrain();
        ArrayList<String> lignesTerrain = this.lireLigneFichier(cheminTerrain);
        this.terrainDeJeu = this.construireGrilleTerrain(lignesTerrain);
    }


    public int obtenirHauteur() {
        return this.terrainDeJeu.length;
    }

    public int obtenirLargeur() {
        return this.terrainDeJeu[0].length;
    }

    public char avoirCodeTuile(int ligne, int colonne) {
        return this.terrainDeJeu[ligne][colonne];
    }

    /**
     * Cherche les bases dans le terrain (tuiles 'b')
     * @return la liste des points correspondant aux bases
     */
    public ArrayList<Point> trouverBases() {
        return this.trouverTuiles('b');
    }

    /**
     * Cherche les entrées des ennemis dans le terrain (tuiles 's')
     * @return la liste des points correspondant aux entrées
     */
    public ArrayList<Point> trouverEntrees() {
        return this.trouverTuiles('s');
    }

    private ArrayList<Point> trouverTuiles(char lettre) {
        ArrayList<Point> points = new ArrayList<>();
        for (int ligne = 0; ligne < this.obtenirHauteur(); ligne++) {
            for (int colonne = 0; colonne < this.obtenirLargeur(); colonne++) {
                if (this.avoirCodeTuile(ligne, colonne) == lettre) {
                    points.add(new Point(colonne, ligne));
                }
            }
        }
        return points;
    }

    /**
     * Convertis les coordoonnées x et y en numéro de ligne et colonne de la tuile.
     * @param x les coordonnéees x
     * @param y les coordonnées y
     * @return un tableau contenant le numéro de ligne et colonne de la tuile.
     */
    public int[] convertirCoordsTuile(int x, int y)  {
        int[] tuile = new int[2];

        int ligne = y / TAILLE_TUILLE;
        int colonne = x / TAILLE_TUILLE;

        tuile[0] = ligne;
        tuile[1] = colonne;

        return tuile;
    }

    public boolean estDansTerrain(int nLigne, int nCol) {
        return nCol >= 0 && nCol < obtenirLargeur() && nLigne >= 0 && nLigne < obtenirHauteur();
    }


    /**
     * Regarde si la tuile est une tuile accessible pour les déplacements ennemi
     * @param nouveauX l'abscisse en pixels
     * @param nouveauY l'ordonnée en pixels
     * @return vrai si la tuile est accessible, faux sinon
     */
    public boolean tuileEstAccessibleCoords(int nouveauX, int nouveauY) {
        /*
         * On regarde avant si les déplacements sortent de la tuile
         * Autrement, l'appel à avoirCodeTuile accèdera à des indices hors limites.
         */

        int ligne = nouveauY / TAILLE_TUILLE;
        int colonne = nouveauX / TAILLE_TUILLE;

        if(!estDansTerrain(ligne,colonne)) {
            return false;
        }
        return (avoirCodeTuile(ligne,colonne) >= '0' && avoirCodeTuile(ligne,colonne) <= '9') || avoirCodeTuile(ligne,colonne) == 'b' || avoirCodeTuile(ligne,colonne) == 's';
    }

    public boolean tuileTourPosable(int x, int y) {
        int ligne = y / TAILLE_TUILLE;
        int colonne = x / TAILLE_TUILLE;
        return (!tuileEstAccessibleCoords(x,y) && !new ArrayList<>(Arrays.asList('c', 'd', 'e', 'f', 'g')).contains(avoirCodeTuile(ligne, colonne)));
    }

    /**
     * Permet de placer des coordonnées sur le centre d'une tuile.
     * @param x les coordonnéees x
     * @param y les coordonnées y
     * @return un tableau, contenant les coordonnées x et y qui correspondent au centre de la tuile.
     */
    public int[] mettreCoordsSurCentreTuile(int x, int y) {
        int[] tabLignesColonnesConverti = this.convertirCoordsTuile(x, y);

        tabLignesColonnesConverti[0] = tabLignesColonnesConverti[0] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
        tabLignesColonnesConverti[1] = tabLignesColonnesConverti[1] * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;

        return tabLignesColonnesConverti;
    }

    public ArrayList<Point> adjacents(Point t) {
        ArrayList<Point> voisins = new ArrayList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // {colonne, ligne}
        for (int[] direction : directions) {
            int nCol = t.getColonne() + direction[0];
            int nLigne = t.getLigne() + direction[1];
            if (estDansTerrain(nLigne, nCol) && tuileEstAccessibleCoords(nCol * TAILLE_TUILLE, nLigne * TAILLE_TUILLE)) {
                voisins.add(new Point(nCol, nLigne));
            }
        }
        return voisins;
    }

}