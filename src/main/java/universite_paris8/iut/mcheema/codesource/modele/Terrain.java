package universite_paris8.iut.mcheema.codesource.modele;

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

    public void initialiseTerrain() {
        /*
        h = sol
        e = glitch1 --
        c = glitch2 /-
        d = glitch3 -\
        f = glitch4 \-
        g = glitch5 -/
        1 = ch_gauche_droite  →
        2 = ch_haut_bas ↓
        3 = ch_droite_bas ↱
        4 = ch_bas_gauche ↴
        5 = ch_gauche_haut ↲
        6 = ch_haut_droite ↳
        7 = ch_haut_droite_bas
        8 = ch_droite_bas_gauche
        9 = ch_bas_gauche_haut
        0 = ch_gauche_haut_droite
        b = base
        e = entrée des ennemis
         */
        switch (this.niveauTerrain) {
            case 1:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','c','h','h','h','d','e','e','c','h','h','h','h','d','e','e'},
                                                 {'e','e','e','e','e','h','h','h','h','h','d','3','1','1','4','h','h','h','h','h'},
                                                 {'e','e','e','e','c','h','h','h','h','h','h','2','h','h','2','h','h','3','1','s'},
                                                 {'e','e','e','c','h','h','h','h','h','h','h','2','h','h','2','h','h','2','h','h'},
                                                 {'e','3','1','1','1','1','1','1','1','4','e','2','e','f','6','1','1','5','g','e'},
                                                 {'e','2','h','h','h','h','d','e','e','2','e','2','e','e','f','h','h','h','e','e'},
                                                 {'h','2','h','3','1','1','4','d','e','2','e','2','e','e','e','f','h','h','e','e'},
                                                 {'h','2','h','2','h','h','2','h','d','2','e','6','1','1','1','1','1','4','e','e'},
                                                 {'h','6','1','5','h','h','2','h','h','2','h','h','h','h','h','h','h','2','e','e'},
                                                 {'h','h','h','h','h','h','2','h','h','2','h','h','h','h','h','h','h','2','e','e'},
                                                 {'b','1','1','1','1','1','5','h','h','6','1','1','1','1','1','1','1','5','e','e'},
                                                 {'h','h','h','h','h','h','h','h','h','h','h','h','h','h','h','g','e','e','e','e'},
                                                 {'h','h','h','h','h','h','h','h','h','h','h','h','h','g','e','e','e','e','e','e'}
                };
                break;
            case 2:
                this.terrainDeJeu = new char[][]{{'d','e','e','e','e','e','e','e','e','e','e','e','e','e','e','s','e','e','e','c'},
                                                 {'h','h','h','h','h','h','h','h','g','e','e','c','h','h','h','2','h','h','h','h'},
                                                 {'h','h','h','h','h','3','1','1','1','1','1','1','1','4','h','2','h','h','h','h'},
                                                 {'h','3','1','1','1','5','g','e','e','c','h','h','h','2','h','6','1','1','4','h'},
                                                 {'h','2','h','h','h','g','e','3','1','1','1','1','1','5','h','h','h','h','2','h'},
                                                 {'h','2','h','3','1','4','e','2','e','h','h','h','h','h','h','h','h','h','2','h'},
                                                 {'h','2','h','2','h','2','e','2','e','h','h','3','1','1','1','1','1','1','5','h'},
                                                 {'b','5','h','2','h','2','d','6','1','4','h','2','h','h','h','h','h','h','h','h'},
                                                 {'h','h','h','2','h','6','4','e','e','6','1','5','h','3','1','1','1','1','4','h'},
                                                 {'h','h','h','2','h','h','2','e','e','h','h','h','h','2','h','h','h','h','2','h'},
                                                 {'h','h','h','2','h','h','6','1','1','1','1','1','1','5','h','3','1','1','5','h'},
                                                 {'b','1','1','5','h','h','h','e','e','f','h','h','h','h','h','2','h','h','h','h'},
                                                 {'h','h','h','h','h','h','h','e','e','e','f','h','h','3','1','5','h','h','h','h'},
                                                 {'h','h','h','h','h','h','g','e','e','e','e','f','h','2','h','h','h','h','h','h'},
                                                 {'h','h','h','h','h','g','e','e','e','e','e','e','h','s','h','h','h','h','h','h'}
                };
                break;
            case 3:
                this.terrainDeJeu = new char[][]{{'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','c','h','h','h','h','h','h','h','h','h','d','e','e','e','e','e','e','e'},
                                                 {'e','e','h','h','3','1','1','1','1','1','4','h','h','d','e','e','e','e','e','e'},
                                                 {'e','e','h','3','5','h','h','h','h','h','6','4','h','h','h','d','e','e','e','e'},
                                                 {'e','e','h','2','h','h','h','h','h','h','h','6','4','h','h','h','h','h','d','e'},
                                                 {'e','e','h','7','1','1','1','1','1','b','h','h','7','1','1','1','1','1','1','s'},
                                                 {'e','e','h','2','h','h','h','h','h','h','h','3','5','h','h','h','h','h','g','e'},
                                                 {'e','e','h','6','4','h','h','h','h','h','3','5','h','h','h','g','e','e','e','e'},
                                                 {'e','e','h','h','6','1','1','1','1','1','5','h','h','g','e','e','e','e','e','e'},
                                                 {'e','e','f','h','h','h','h','h','h','h','h','h','g','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'},
                                                 {'e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e','e'}};
                break;
        }
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